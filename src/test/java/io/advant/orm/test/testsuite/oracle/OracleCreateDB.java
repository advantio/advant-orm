package io.advant.orm.test.testsuite.oracle;

import io.advant.orm.DB;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestCreateDB;
import io.advant.orm.type.DBType;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
public class OracleCreateDB {

    private static TestCreateDB test;

    @BeforeClass
    public static void connect() {
        PrintUtil.suite(OracleCreateDB.class.getName());
        try {
            Class.forName(DBType.ORACLE.getDriver());
        } catch (ClassNotFoundException e) {
            Assume.assumeTrue("Oracle Driver not available [not mandatory]", false);
        }
        Connection connection = null;
        try {
            connection = DB.newInstance(new OracleHostParams(), DefaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            Assume.assumeTrue("Connection to Oracle database is not available [not mandatory]", false);
        }
        test = new TestCreateDB(connection);
    }

    @Test
    public void create() throws OrmException {
        test.create("ORACLE");
    }

    @Test
    public void procedure() throws OrmException {
        test.procedure("ORACLE");
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }

}
