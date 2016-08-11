package io.advant.orm.test.testsuite.h2;

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
public class H2TestPerformance {

    private static TestPerformance test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(H2TestPerformance.class.getName());
        DBConnection connection = DB.newInstance(new H2LocalParams(), DefaultParams.getEntities()).getConnection();
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
