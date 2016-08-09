package io.advant.orm.test.testsuite.hsqldb;

import io.advant.orm.DB;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDropDB;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
public class HSQLDBDropDB {

    private static TestDropDB test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(HSQLDBDropDB.class.getName());
        Connection connection = DB.newInstance(new HSQLDBLocalParams(), DefaultParams.getEntities()).getConnection();
        test = new TestDropDB(connection);
    }

    @Test
    public void drop() throws ConnectionException, OrmException {
        test.drop("HSQLDB");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }
}
