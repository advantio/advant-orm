package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.DB;
import io.advant.orm.DBHostParams;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestCreateDB;
import io.advant.orm.type.DBHostType;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class MysqlCreateDB {

    private static TestCreateDB test;

    @BeforeClass
    public static void connect() {
        PrintUtil.suite(MysqlCreateDB.class.getName());
        DefaultParams defaultParams = new DefaultParams();
        Connection connection = null;
        try {
            connection = DB.newInstance(new MysqlHostParams(), defaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            System.out.println("Connection to Mysql database is not available [not mandatory]");
            Assume.assumeTrue(false);
        }
        test = new TestCreateDB(connection);
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        test.create("MYSQL");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }

}
