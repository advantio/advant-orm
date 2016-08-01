package io.advant.orm.test.testcase;

import io.advant.orm.Query;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class TestDropDB {

    private final Connection connection;

    public TestDropDB(Connection connection) {
        this.connection = connection;
    }

    public void drop(String fileName) throws ConnectionException, OrmException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + fileName + "drop.sql");
        Query.runScript(connection, inputStream);
    }
}
