package io.advant.orm.test.testsuite.derby;

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
 * @author Marco Romagnolo
 */
public class DerbyCreateTables {

    private static TestCreateTables test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(DerbyCreateTables.class.getName());
        Connection connection = DBFactory.newInstance(new DerbyConfig()).getConnection();
        test = new TestCreateTables(connection);
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        test.create("DERBY");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DBFactory.getInstance().disconnect();
    }

}
