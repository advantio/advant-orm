package io.advant.orm;

import io.advant.orm.exception.OrmException;
import io.advant.orm.internal.SqlProcessor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marco on 29/07/2016.
 */
public class Query {

    private static final Logger LOGGER = Logger.getLogger(Query.class.getName());

    public static int exec(Connection connection, String sql) throws OrmException {
        try {
            return SqlProcessor.exec(connection, sql);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrmException(e);
        }
    }

    public static int exec(Connection connection, InputStream inputStream) throws OrmException {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String sql = result.toString("UTF-8");
            return SqlProcessor.exec(connection, sql);
        } catch (SQLException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrmException(e);
        }
    }

}
