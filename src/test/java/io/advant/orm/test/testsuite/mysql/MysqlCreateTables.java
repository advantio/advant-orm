package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.DB;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestCreateTables;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class MysqlCreateTables {

    private static TestCreateTables test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(MysqlCreateTables.class.getName());
        Connection connection = DB.newInstance(new MysqlHostParams(), DefaultParams.getEntities()).getConnection();
        test = new TestCreateTables(connection);
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
