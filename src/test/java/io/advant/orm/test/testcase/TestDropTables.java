package io.advant.orm.test.testcase;

import io.advant.orm.Query;
import io.advant.orm.exception.OrmException;

import java.io.InputStream;
import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
public class TestDropTables {

    private final Connection connection;

    public TestDropTables(Connection connection) {
        this.connection = connection;
    }

    public void drop(String fileName) throws OrmException {
        PrintUtil.test("Drop tables");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + fileName + "/drop.sql");
        Query.run(connection, inputStream, true);
    }
}
