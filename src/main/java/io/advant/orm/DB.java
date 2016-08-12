package io.advant.orm;

import io.advant.orm.exception.ConnectionException;

import java.sql.SQLException;

/**
 * @author Marco Romagnolo
 */
public interface DB {

    boolean isConnected();

    void connect() throws SQLException;

    DBConnection getConnection() throws ConnectionException;

    void disconnect();
}
