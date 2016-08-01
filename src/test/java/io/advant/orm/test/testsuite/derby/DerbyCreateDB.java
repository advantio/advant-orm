package io.advant.orm.test.testsuite.derby;

import io.advant.orm.DB;
import io.advant.orm.DBLocalParams;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
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
public class DerbyCreateDB {

    private static TestCreateDB test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        DefaultParams defaultParams = new DefaultParams("memory:" + DefaultParams.DATABASE + ";create=true;user=test;password=test");
        DBLocalParams params = defaultParams.getDBLocalParams(DBLocalType.DERBY);
        Connection connection = DB.newInstance(params, defaultParams.getEntities()).getConnection();
        test = new TestCreateDB(connection);
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        System.out.println(getClass().getName() + ": Create tables test");
        test.create("DERBY");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException, SQLException {
        DB.getInstance().disconnect();
    }

}
