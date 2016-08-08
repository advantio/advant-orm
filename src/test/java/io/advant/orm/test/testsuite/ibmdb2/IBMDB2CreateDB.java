package io.advant.orm.test.testsuite.ibmdb2;

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
 * @author Marco Romagnolo
 */
public class IBMDB2CreateDB {

    private static TestCreateDB test;

    @BeforeClass
    public static void connect() throws ConnectionException {
        PrintUtil.suite(IBMDB2CreateDB.class.getName());
        try {
            Class.forName(DBHostType.IBMDB2.getDriver());
        } catch (ClassNotFoundException e) {
            Assume.assumeTrue("DB2 Driver not available [not mandatory]", false);
        }
        Connection connection = null;
        try {
            connection = DB.newInstance(new IBMDB2HostParams(), DefaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            Assume.assumeTrue("Connection to IBM DB2 database is not available [not mandatory]", false);
        }
        test = new TestCreateDB(connection);
    }

    @Test
    public void create() throws OrmException {
        test.create("IBMDB2");
    }

//    @Test
//    public void procedure() throws OrmException {
//        test.procedure("IBMDB2");
//    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }

}
