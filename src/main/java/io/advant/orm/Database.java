package io.advant.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.advant.orm.dao.EntityReflect;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.TableParseException;

/**
 * @author Marco Romagnolo
 */
public class Database {

    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
    private static Database instance;
    private Params params;
    private Connection connection;

    private Database(Params params) {
        this.params = params;
        try {
            if (params.getEntities()!=null) {
                for (String entity : params.getEntities()) {
                    Class<?> entityClass = Class.forName(entity);
                    EntityReflect.getInstance(entityClass);
                }
            }
        } catch (ClassNotFoundException | TableParseException | NoSuchFieldException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static Database getInstance() throws ConnectionException {
        return instance;
    }

    public Connection getConnection() throws ConnectionException {
        if (connection == null) {
            if (params.getProperties() == null) {
                params.setProperties(new Properties());
            }
            if (params.getUser() != null) {
                params.getProperties().put("user", params.getUser());
            }
            if (params.getPassword() != null) {
                params.getProperties().put("password", params.getPassword());
            }
            try {
                Class.forName(params.getDbType().getDriver());
                switch (params.getDbType()) {
                    case MYSQL:
                        connection = DriverManager.getConnection(getUrl("jdbc:mysql://", params.getHost(), params.getPort(), params.getDatabase()), params.getProperties());
                        break;
                    case POSTGRESQL:
                        connection = DriverManager.getConnection(getUrl("jdbc:postgresql://", params.getHost(), params.getPort(), params.getDatabase()), params.getProperties());
                        break;
                    case IBMDB2:
                        connection = DriverManager.getConnection(getUrl("jdbc:db2://", params.getHost(), params.getPort(), params.getDatabase()), params.getProperties());
                        break;
                    case MSSQL:
                        connection = DriverManager.getConnection(getUrl("jdbc:microsoft:sqlserver://", params.getHost(), params.getPort(), params.getDatabase()), params.getProperties());
                        break;
                    case ORACLE:
                        connection = DriverManager.getConnection(getUrl("jdbc:oracle:thin:@", params.getHost(), params.getPort(), params.getDatabase()), params.getProperties());
                        break;
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new ConnectionException(e);
            }
        }
        return connection;
    }

    private String getUrl(String start, String host, int port, String database) {
        return start + host + ":" + port + "/" + database;
    }

    public static Database newInstance(Params params) throws ConnectionException {
        instance = new Database(params);
        return instance;
    }

    public void disconnect() throws SQLException {
        if (connection!=null) {
            connection.close();
        }
    }

    public void begin() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
}
