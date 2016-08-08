/**
 * Copyright 2016 Advant I/O
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.advant.orm.internal;

import io.advant.orm.AbstractTable;
import io.advant.orm.Entity;
import io.advant.orm.exception.TableParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class SqlProcessor {

    private static final Logger LOGGER = Logger.getLogger(SqlProcessor.class.getName());
    private final EntityReflect<? extends Entity> reflect;
    private final Connection connection;
    private PreparedStatement pstmt;

    public SqlProcessor(Connection connection, EntityReflect<? extends Entity> reflect) {
        this.connection = connection;
        this.reflect = reflect;
    }

    public int deleteAll() throws SQLException {
        String sql = "DELETE FROM " + reflect.getTable();
        pstmt = connection.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    public ResultSet select(Conditions conditions) throws SQLException, NoSuchFieldException, TableParseException {
        String select = "SELECT";
        String from = "FROM " + reflect.getTable();
        String join = "";
        Set<ColumnData> columns = new HashSet<>();
        columns.addAll(reflect.getColumns());
        getTableColumnsForSelect(columns, reflect);
        List<JoinData> joins = new ArrayList<>();
        getJoinsForSelect(reflect, joins, new JoinData(null, null, reflect.getTable(), null));
        Iterator<ColumnData> iterator = columns.iterator();
        while (iterator.hasNext()) {
            ColumnData column = iterator.next();
            boolean isLastColumn = !iterator.hasNext() && !iterator.hasNext();
            select += " " + column.getTable() + "." + column.getColumn() + " AS " + column.getTableIndex() + "_"
                    + column.getColumn() + (isLastColumn ? " " : ",");
        }
        for (JoinData joinData : joins) {
            join += " LEFT JOIN " + joinData.getJoinTable() //+ " AS " + joinData.getJoinTable()
                    + " ON " + joinData.getJoinTable() + "." + joinData.getJoinColumn()
                    + "=" + joinData.getTable() + "." + joinData.getColumn();
        }
        String where = conditions != null ? " WHERE " + conditions.asSQL() + " " : "";
        String sql = select + from + join + where;
        LOGGER.fine(sql);
        pstmt = connection.prepareStatement(sql);
        // Adding Conditions statements
        if (conditions != null) {
            int i = 0;
            for (Condition condition : conditions.getList()) {
                pstmt.setObject(++i, condition.getValue());
            }
        }
        return pstmt.executeQuery();
    }

    private void getTableColumnsForSelect(Set<ColumnData> columns, EntityReflect<? extends Entity> entityReflect) throws TableParseException, NoSuchFieldException {
        for (Class joinEntitiyClass : entityReflect.getJoinEntities().values()) {
            EntityReflect joinEntityReflect = EntityReflect.getInstance(joinEntitiyClass);
            columns.addAll(joinEntityReflect.getColumns());
            getTableColumnsForSelect(columns, joinEntityReflect);
        }
    }

    private void getJoinsForSelect(EntityReflect<? extends Entity> reflect, List<JoinData> joins, JoinData doNotJoin) throws TableParseException, NoSuchFieldException {
        for (JoinData join : reflect.getJoins()) {
            if (!joins.contains(join) && !join.equals(doNotJoin)) {
                joins.add(join);
            }
        }
        for (Class joinEntityClass : reflect.getJoinEntities().values()) {
            EntityReflect joinEntityReflect = EntityReflect.getInstance(joinEntityClass);
            getJoinsForSelect(joinEntityReflect, joins, doNotJoin);
        }
    }

    public <T extends Entity> void insert(T entity, List<ColumnData> columnsData) throws SQLException, IllegalAccessException {
        String sql = "INSERT INTO " + reflect.getTable();
        String columns = " (";
        String values = " (";
        for (ColumnData columnData : columnsData) {
            if (columnData.isId() && columnData.getValue() == null) {
                continue;
            }
            columns += columnData.getColumn() + ",";
            values += "?,";
        }
        columns = columns.substring(0, columns.length()-1) + ")";
        values = values.substring(0, values.length()-1) + ")";
        sql += columns + " VALUES " + values;
        //pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt = connection.prepareStatement(sql, new String[]{"id"});
        int i = 0;
        for (ColumnData columnData : columnsData) {
            Object value = columnData.getValue();
            if (columnData.isId() && columnData.getValue() == null) {
                continue;
            } else if (columnData.isVersion()) {
                value = 1L;
            }
            ValueBridge.setStatement(pstmt, ++i, columnData.getValueType(), value);
        }
        entity.setVersion(1L);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (entity.getId() == null) {
            if (rs.next()) {
                Object id = rs.getObject(1);
                if (id instanceof Number) {
                    entity.setId(((Number) id).longValue());
                }
            }
        }
        rs.close();
    }

    public <T extends Entity> int update(T entity, List<ColumnData> columnsData) throws IllegalAccessException, SQLException {
        String sql = "UPDATE " + reflect.getTable() + " SET ";
        for (ColumnData columnData : columnsData) {
            if(!columnData.isId()){
                sql += columnData.getColumn() + " = ?,";
            }
        }
        sql = sql.substring(0, sql.length()-1);
        sql += " WHERE id = ?";
        pstmt = connection.prepareStatement(sql);
        int i = 0;
        for (ColumnData columnData : columnsData) {
            if (!columnData.isId()) {
                if (columnData.isVersion()) {
                    long version = ((Long) columnData.getValue()) + 1L;
                    pstmt.setLong(++i, version);
                    entity.setVersion(version);
                } else {
                    ValueBridge.setStatement(pstmt, ++i, columnData.getValueType(), columnData.getValue());
                }
            }
        }
        long idValue = ((AbstractTable) entity).getLastId();
        pstmt.setLong(++i, idValue);
        return pstmt.executeUpdate();
    }

    public <T extends Entity> int delete(T entity) throws SQLException {
        String sql = "DELETE FROM " + reflect.getTable() + " WHERE id=?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setObject(1, (entity).getId());
        return pstmt.executeUpdate();
    }

    public void close() throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
    }
}
