package io.advant.orm;

import io.advant.orm.exception.ConnectionException;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Marco Romagnolo
 */
public interface DB {

    boolean isConnected();

    DataSource getDataSource();

    void connect() throws SQLException;

    DBConnection getConnection() throws ConnectionException;

    void disconnect();
}
