package io.advant.orm.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class ExecuteUpdate {

    private static final Logger LOGGER = Logger.getLogger(SqlProcessor.class.getName());

    public static int run(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        int row = stmt.executeUpdate(sql);
        stmt.close();
        return row;
    }

    public static int run(Connection connection, String sql, Object[] values) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int i = 0;
        for (Object value : values) {
            pstmt.setObject(++i, value);
        }
        int row = pstmt.executeUpdate(sql);
        pstmt.close();
        return row;
    }

    public static void run(Connection connection, String[] queries) throws SQLException {
        Statement stmt = connection.createStatement();
        for (String query : queries) {
            stmt.execute(query);
        }
        stmt.close();
    }

    public static boolean call(Connection connection, String sql) throws SQLException {
        CallableStatement cstmt = connection.prepareCall(sql);
        boolean result = cstmt.execute();
        cstmt.close();
        return result;
    }

    public static void runScript(Connection connection, String[] array, boolean exitOnError) throws SQLException {
        StringBuffer command = null;
        String delimiter = ";";
        int lineNumber =0;
        for (String line : array) {
            lineNumber++;
            if (line.isEmpty()) continue;
            if (command == null) {
                command = new StringBuffer();
            }
            String trimmedLine = line.trim();
            if (trimmedLine.length() >= 1 && !trimmedLine.startsWith("//") && !trimmedLine.startsWith("--")) {
                if (!trimmedLine.isEmpty() && trimmedLine.endsWith(delimiter)) {
                    command.append(line.substring(0, line.lastIndexOf(delimiter)));
                    command.append(" ");
                    Statement statement = connection.createStatement();
                    try {
                        statement.execute(command.toString());
                    } catch (SQLException e) {
                        final String errText = String.format("Error executing '%s' (line %d): %s", command, lineNumber, e.getMessage());
                        if (exitOnError) {
                            throw new SQLException(errText, e);
                        }
                    } finally {
                        if (statement!=null) {
                            statement.close();
                        }
                    }
                    command = null;
                } else {
                    command.append(line);
                }
            }
        }
    }
}
