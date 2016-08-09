package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDAO;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MysqlTestDAO {

    private static TestDAO test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(MysqlTestDAO.class.getName());
        DBConnection connection = DB.newInstance(new MysqlHostParams(), DefaultParams.getEntities()).getConnection();
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
