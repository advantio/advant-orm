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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
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
    private Statement stmt;
    private PreparedStatement pstmt;
    private CallableStatement cstmt;

    public SqlProcessor(Connection connection, EntityReflect<? extends Entity> reflect) {
        this.connection = connection;
        this.reflect = reflect;
    }

    public static boolean runScript(Connection connection, InputStream input) throws SQLException{
        ScriptRunner s = new ScriptRunner(connection, true, true);
        try {
            s.runScript(new InputStreamReader(input));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return false;
    }

    public static int[] execBatch(Connection connection, String[] queries) throws SQLException {
        Statement stmt = connection.createStatement();
        for (String query : queries) {
            stmt.addBatch(query);
        }
        int[] result = stmt.executeBatch();
        stmt.close();
        return result;
    }

    public static int[] execBatch(Connection connection, String query, Object[][] values) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        for (int i=0; i<values.length; i++) {
            for (int j=0; j<values[i].length; j++) {
                Object value = values[i][j];
                pstmt.setObject(j, value);
            }
            pstmt.addBatch(query);
        }
        int[] result = pstmt.executeBatch();
        pstmt.close();
        return result;
    }

    public static int[] callBatch(Connection connection, String query, Object[][] values) throws SQLException {
        CallableStatement cstmt = connection.prepareCall(query);
        for (int i=0; i<values.length; i++) {
            for (int j=0; j<values[i].length; j++) {
                Object value = values[i][j];
                cstmt.setObject(j, value);
            }
            cstmt.addBatch(query);
        }
        int[] result = cstmt.executeBatch();
        cstmt.close();
        return result;
    }

    public int exec(String sql) throws SQLException {
        stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }

    public ResultSet call(String sql) throws SQLException {
        cstmt = connection.prepareCall(sql);
        return cstmt.executeQuery();
    }

    public int truncate(boolean force) throws SQLException {
        String cascade = force ? "" : " CASCADE";
        String sql = "TRUNCATE TABLE " + reflect.getTable() + cascade;
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
                columns += columnData.getColumn() + ",";
                values += "?,";
            }
        }
        columns = columns.substring(0, columns.length()-1) + ")";
        values = values.substring(0, values.length()-1) + ")";
        sql += columns + " VALUES " + values;
        pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        int i = 0;
        for (ColumnData columnData : columnsData) {
            Object value = columnData.getValue();
            if (columnData.isId()) {
                idIsNull = (value == null);
                continue;
            } else if (columnData.isVersion()) {
                value = 1L;
            }
            SqlValue.setStatement(pstmt, ++i, value);
        }
        pstmt.executeUpdate();
        if (idIsNull) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }
            rs.close();
        }
    }



    public int update(List<ColumnData> columnsData) throws IllegalAccessException, SQLException {
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
        return pstmt.executeUpdate();
    }

    public <T extends Entity> int delete(T entity) throws SQLException {
        String sql = "DELETE FROM " + reflect.getTable() + " WHERE id=?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setObject(1, (entity).getId());
        return pstmt.executeUpdate();
    }

    public void  close() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (cstmt != null) {
            cstmt.close();
        }
    }
}