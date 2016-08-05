package io.advant.orm.internal;

import java.sql.*;

/**
 * @author Marco Romagnolo
 */
public class ExecuteBatch {

    public static int[] run(Connection connection, String[] queries) throws SQLException {
        Statement stmt = connection.createStatement();
        for (String query : queries) {
            stmt.addBatch(query);
        }
        int[] result = stmt.executeBatch();
        stmt.close();
        return result;
    }

    public static int[] run(Connection connection, String query, Object[][] values) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        for (int i=0; i<values.length; i++) {
            for (int j=0; j<values[i].length; j++) {
                Object value = values[i][j];
                pstmt.setObject(j, value);
            }
            pstmt.addBatch();
        }
        int[] result = pstmt.executeBatch();
        pstmt.close();
        return result;
    }

    public static int[] call(Connection connection, String query, Object[][] values) throws SQLException {
        CallableStatement cstmt = connection.prepareCall(query);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                Object value = values[i][j];
                cstmt.setObject(j, value);
            }
            cstmt.addBatch();
        }
        int[] result = cstmt.executeBatch();
        cstmt.close();
        return result;
    }

}
