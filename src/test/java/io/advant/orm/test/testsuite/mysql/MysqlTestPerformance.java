package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.test.service.ServiceException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestPerformance;
import io.advant.orm.test.testcase.TestService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MysqlTestPerformance {

    private static TestPerformance test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(MysqlTestPerformance.class.getName());
        DBConnection connection = DB.newInstance(new MysqlHostParams(), DefaultParams.getEntities()).getConnection();
        test = new TestPerformance(connection);
    }

    @Test
    public void test1() throws ServiceException {
        test.test1();
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DB.getInstance().disconnect();
    }
}
