package io.advant.orm.test.testsuite.postgresql;

import io.advant.orm.DB;
import io.advant.orm.DBHostParams;
import io.advant.orm.DBLocalParams;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDropDB;
import io.advant.orm.type.DBHostType;
import io.advant.orm.type.DBLocalType;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Marco Romagnolo
 */
public class PostgresqlDropDB {

    private static TestDropDB test;

    @BeforeClass
    public static void connect() {
        PrintUtil.suite(PostgresqlDropDB.class.getName());
        Connection connection = null;
        try {
            connection = DB.newInstance(new PostgresqlHostParams(), DefaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            System.out.println("Connection to Postgresql database is not available [not mandatory]");
            Assume.assumeTrue(false);
        }
        test = new TestDropDB(connection);
    }

    @Test
    public void drop() throws ConnectionException, OrmException {
        test.drop("POSTGRESQL");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }
}
