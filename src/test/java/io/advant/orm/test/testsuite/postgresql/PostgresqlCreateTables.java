package io.advant.orm.test.testsuite.postgresql;

import io.advant.orm.DBFactory;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestCreateTables;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class PostgresqlCreateTables {

    private static TestCreateTables test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(PostgresqlCreateTables.class.getName());
        Connection connection = DBFactory.newInstance(new PostgresqlConfig()).getConnection();
        test = new TestCreateTables(connection);
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        test.create("POSTGRESQL");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DBFactory.getInstance().disconnect();
    }

}