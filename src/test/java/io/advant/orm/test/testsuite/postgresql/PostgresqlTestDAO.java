package io.advant.orm.test.testsuite.postgresql;

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
public class PostgresqlTestDAO {

    private static TestDAO test;

    @BeforeClass
    public static void configure() {
        PrintUtil.suite(PostgresqlTestDAO.class.getName());
        Connection connection = null;
        try {
            connection = DB.newInstance(new PostgresqlHostParams(), DefaultParams.getEntities()).getConnection();
        } catch (ConnectionException e) {
            System.out.println("Connection to Postgresql database is not available [not mandatory]");
            Assume.assumeTrue(false);
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
