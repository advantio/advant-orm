package io.advant.orm.test.testsuite.postgresql;

import io.advant.orm.DB;
import io.advant.orm.DBHostParams;
import io.advant.orm.DBLocalParams;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestCreateDB;
import io.advant.orm.type.DBHostType;
import io.advant.orm.type.DBLocalType;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Marco on 29/07/2016.
 */
public class PostgresqlCreateDB {

    private static TestCreateDB test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(PostgresqlCreateDB.class.getName());
        DefaultParams defaultParams = new DefaultParams();
        DBHostParams params = defaultParams.getDBHostParams(DBHostType.POSTGRESQL, 5432);
        Connection connection = null;
        try {
            connection = DB.newInstance(params, defaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            System.out.println("Connection to Postgresql database is not available [not mandatory]");
            Assume.assumeTrue(false);
        }
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
