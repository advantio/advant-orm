package io.advant.orm.test.testsuite.oracle;

import io.advant.orm.DBFactory;
import io.advant.orm.DBConnection;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.test.service.ServiceException;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestService;
import io.advant.orm.type.DBType;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OracleTestService {

    private static TestService test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(OracleTestService.class.getName());
        try {
            Class.forName(DBType.ORACLE.getDriver());
        } catch (ClassNotFoundException e) {
            Assume.assumeTrue("Oracle Driver not available [not mandatory]", false);
        }
        DBConnection connection = null;
        try {
            connection = DBFactory.newInstance(new OracleConfig()).getConnection();
        } catch (ConnectionException e) {
            Assume.assumeTrue("Connection to Oracle database is not available [not mandatory]", false);
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
    public void test4_update() throws ServiceException {
        test.update();
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

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DBFactory.getInstance().disconnect();
    }
}
