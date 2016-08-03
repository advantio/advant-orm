package io.advant.orm.test.testsuite.hsqldb;

import io.advant.orm.DB;
import io.advant.orm.DBLocalParams;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestCreateDB;
import io.advant.orm.type.DBLocalType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Marco on 29/07/2016.
 */
public class HSQLDBCreateDB {

    private static TestCreateDB test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(HSQLDBCreateDB.class.getName());
        DefaultParams defaultParams = new DefaultParams("mem:" + DefaultParams.DATABASE);
        DBLocalParams params = defaultParams.getDBLocalParams(DBLocalType.HSQLDB);
        Connection connection = DB.newInstance(params, defaultParams.getEntities()).getConnection();
        test = new TestCreateDB(connection);
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        test.create("HSQLDB");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException, SQLException {
        DB.getInstance().disconnect();
    }

}
