package io.advant.orm.test.testsuite.postgresql;

import io.advant.orm.DB;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestCreateDB;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class PostgresqlCreateDB {

    private static TestCreateDB test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(PostgresqlCreateDB.class.getName());
        Connection connection = DB.newInstance(new PostgresqlHostParams(), DefaultParams.getEntities()).getConnection();
        test = new TestCreateDB(connection);
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        test.create("POSTGRESQL");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }

}
