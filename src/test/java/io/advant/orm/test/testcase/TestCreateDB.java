package io.advant.orm.test.testcase;

import io.advant.orm.Query;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;

import java.io.InputStream;
import java.sql.Connection;

/**
 *
 */
public class TestCreateDB {

    private Connection connection;

    public TestCreateDB(Connection connection) {
        this.connection = connection;
    }

    public void create(String fileName) throws ConnectionException, OrmException {
        PrintUtil.test("Create tables");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + fileName + "/create.sql");
        Query.runScript(connection, inputStream);
    }
}
