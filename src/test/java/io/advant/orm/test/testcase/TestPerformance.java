package io.advant.orm.test.testcase;

import io.advant.orm.DBConnection;
import io.advant.orm.test.service.PerformanceService;
import io.advant.orm.test.service.ServiceException;

/**
 * @author Marco Romagnolo
 */
public class TestPerformance {

    private final PerformanceService productService;

    public TestPerformance(DBConnection connection) {
        productService = new PerformanceService(connection);
    }

    public void test1() throws ServiceException {
        PrintUtil.test("Performance - Test1");
        productService.test1();
    }

}
