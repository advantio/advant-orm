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

import io.advant.orm.Entity;
import io.advant.orm.exception.TableParseException;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 */
public class SqlProcessor {

    private static final Logger LOGGER = Logger.getLogger(SqlProcessor.class.getName());
    private final EntityReflect<? extends Entity> reflect;
    private final Connection connection;
    private PreparedStatement pstmt;
    private Statement stmt;

    public SqlProcessor(Connection connection, EntityReflect<? extends Entity> reflect) {
        this.connection = connection;
        this.reflect = reflect;
    }

    public void truncate(boolean force) throws SQLException {
        String cascade = force ? "" : " CASCADE";
        String sql = "TRUNCATE TABLE " + reflect.getTable() + cascade;
        pstmt = connection.prepareStatement(sql);
        pstmt.executeUpdate();
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
            select += " " + column.getTable() + "." + column.getColumn() + " AS " + column.getTable() + "_"
                    + column.getColumn() + (isLastColumn ? " " : ",");
        }
        for (JoinData joinData : joins) {
            join += " LEFT JOIN " + joinData.getJoinTable() + " AS " + joinData.getJoinTable()
                    + " ON " + joinData.getJoinTable() + "." + joinData.getJoinColumn()
                    + "=" + joinData.getTable() + "." + joinData.getColumn();
        }
        String where = conditions != null ? " WHERE " + conditions.asSQL() + " " : "";
        String sql = select + from + join + where;
        LOGGER.fine(sql);
        pstmt = connection.prepareStatement(sql);
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
        boolean idIsNull = false;
        for (ColumnData columnData : columnsData) {
            if (!columnData.isId()) {
                columns += columnData + ",";
                values += "?,";
            }
        }
        columns = columns.substring(0, columns.length()-1) + ")";
        values = values.substring(0, values.length()-1) + ")";
        sql += columns + " VALUES " + values;
        pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        int i = 0;
        for (ColumnData columnData : columnsData) {
            if (columnData.isId()) {
                idIsNull = columnData.getValue() == null;
            } else {
                Object value;
                if (columnData.isVersion()) {
                    value = 1L;
                } else {
                    value = (columnData.getValue() == null) ? "NULL" : "'" + Parser.parse(columnData.getValue()) + "'";
                }
                pstmt.setObject(++i, value);
            }
        }
        pstmt.executeQuery();
        if (idIsNull) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }
            rs.close();
        }
    }

    public <T extends Entity> void update(T entity, List<ColumnData> columnsData) throws IllegalAccessException, SQLException {
        String sql = "UPDATE " + reflect.getTable() + " SET ";
        for (ColumnData columnData : columnsData) {
            if(!columnData.isId()){
                sql += columnData.getColumn() + " = ?,";
            }
        }
        sql += " WHERE id = ?";
        pstmt = connection.prepareStatement(sql);
        Object id = null;
        int i = 0;
        for (ColumnData columnData : columnsData) {
            if(columnData.isId()){
                id = columnData.getColumn();
            } else if (columnData.isVersion()) {
                Long value = ((Long) columnData.getValue()) + 1L;
                pstmt.setObject(++i, value);
            } else {
                Object value = (columnData.getValue() == null) ? "NULL" : "'" + Parser.parse(columnData.getValue()) + "'";
                pstmt.setObject(++i, value);
            }
        }
        pstmt.setObject(++i, id);
        pstmt.executeUpdate();
    }

    public <T extends Entity> void delete(T entity) throws SQLException {
        String sql = "DELETE FROM " + reflect.getTable() + " WHERE id=?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setObject(1, (entity).getId());
        pstmt.executeUpdate();
    }

    public void  close() throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
        if (stmt != null) {
            stmt.close();
        }
    }
}
