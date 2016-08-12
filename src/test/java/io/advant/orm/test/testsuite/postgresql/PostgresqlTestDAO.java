package io.advant.orm.test.testsuite.postgresql;

import io.advant.orm.DBFactory;
import io.advant.orm.DBConnection;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostgresqlTestDAO {

    private static TestDAO test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(PostgresqlTestDAO.class.getName());
        DBConnection connection = DBFactory.newInstance(new PostgresqlConfig()).getConnection();
        test = new TestDAO(connection);
    }

    @Test
    public void test1_insert() throws OrmException {
        test.insert();
    }

    @Test
    public void test2_findAll() throws OrmException {
        test.findAll();
    }

    @Test
    public void test3_find() throws OrmException {
        test.find();
    }

    @Test
    public void test4_update() throws OrmException {
        test.update();
    }

    @Test
    public void test5_delete() throws OrmException {
        test.delete();
    }

    @Test
    public void test6_deleteAll() throws OrmException {
        test.deleteAll();
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DBFactory.getInstance().disconnect();
    }
}
