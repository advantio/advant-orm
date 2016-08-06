package io.advant.orm.test.testsuite.ibmdb2;

import io.advant.orm.DB;
import io.advant.orm.DBHostParams;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDAO;
import io.advant.orm.type.DBHostType;
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
        DefaultParams defaultParams = new DefaultParams();
        DBHostParams params = defaultParams.getDBHostParams(DBHostType.IBMDB2, 50000);
        Connection connection = null;
        try {
            connection = DB.newInstance(params, defaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            Assume.assumeTrue("Connection to IBM DB2 database is not available [not mandatory]", false);
        }
        test = new TestDAO(connection);
    }

    @Test
    @Ignore("DB2 unsupported")
    public void test1_clear() throws OrmException {
        test.clear();
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
