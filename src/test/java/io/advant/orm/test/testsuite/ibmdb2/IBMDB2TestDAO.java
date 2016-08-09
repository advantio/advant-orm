package io.advant.orm.test.testsuite.ibmdb2;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDAO;
import io.advant.orm.type.DBType;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IBMDB2TestDAO {

    private static TestDAO test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(IBMDB2TestDAO.class.getName());
        try {
            Class.forName(DBType.IBMDB2.getDriver());
        } catch (ClassNotFoundException e) {
            Assume.assumeTrue("DB2 Driver not available [not mandatory]", false);
        }
        DBConnection connection = null;
        try {
            connection = DB.newInstance(new IBMDB2HostParams(), DefaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            Assume.assumeTrue("Connection to IBM DB2 database is not available [not mandatory]", false);
        }
        test = new TestDAO(connection);
    }

    @Test
    public void test1_deleteAll() throws OrmException {
        test.deleteAll();
    }

    @Test
    public void test2_insert() throws OrmException {
        test.insert();
    }

    @Test
    public void test3_findAll() throws OrmException {
        test.findAll();
    }

    @Test
    public void test4_find() throws OrmException {
        test.find();
    }

    @Test
    public void test5_update() throws OrmException {
        test.update();
    }

    @Test
    public void test6_delete() throws OrmException {
        test.delete();
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }
}
