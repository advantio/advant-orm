package io.advant.orm.test.testsuite.ibmdb2;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.test.service.ServiceException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestService;
import io.advant.orm.test.testsuite.h2.H2LocalParams;
import io.advant.orm.type.DBType;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IBMDB2TestService {

    private static TestService test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(IBMDB2TestService.class.getName());
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
        test = new TestService(connection);
    }

    @Test
    public void test1_insert() throws ServiceException {
        test.insert();
    }

    @Test
    public void test2_findAll() throws ServiceException {
        test.findAll();
    }

    @Test
    public void test3_find() throws ServiceException {
        test.find();
    }

    @Test
    public void test5_save() throws ServiceException {
        test.save();
    }

    @Test
    public void test6_delete() throws ServiceException {
        test.delete();
    }

    @Test
    public void test7_deleteAll() throws ServiceException {
        test.deleteAll();
    }

    @Test
    public void test6_deleteAll() throws ServiceException {
        test.deleteAll();
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }
}
