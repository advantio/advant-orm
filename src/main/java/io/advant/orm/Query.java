package io.advant.orm;

import io.advant.orm.exception.OrmException;
import io.advant.orm.internal.SqlProcessor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marco on 29/07/2016.
 */
public class Query {

    private static final Logger LOGGER = Logger.getLogger(Query.class.getName());

    public static void runScript(Connection connection, InputStream inputStream) throws OrmException {
        try {
            if (inputStream!=null) {
                SqlProcessor.runScript(connection, inputStream);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrmException(e);
        }
    }

    public static int[] exec(Connection connection, String queries[]) throws OrmException {
        try {
            return SqlProcessor.execBatch(connection, queries);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrmException(e);
        }
    }

    public static int[] exec(Connection connection, String query, Object[][] values) throws OrmException {
        try {
            return SqlProcessor.execBatch(connection, query, values);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrmException(e);
        }
    }

    public static int[] call(Connection connection, String query, Object values[][]) throws OrmException {
        try {
            return SqlProcessor.callBatch(connection, query, values);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrmException(e);
        }
    }

    private static String streamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            byteArray.write(buffer, 0, length);
        }
        return byteArray.toString("UTF-8");
    }

}
