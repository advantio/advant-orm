package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.DB;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDropTables;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
public class MysqlDropTables {

    private static TestDropTables test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(MysqlDropTables.class.getName());
        Connection connection = DB.newInstance(new MysqlHostParams(), DefaultParams.getEntities()).getConnection();
        test = new TestDropTables(connection);
    }

    @Test
    public void drop() throws ConnectionException, OrmException {
        test.drop("MYSQL");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }
}
