package io.advant.orm.test.testcase;

import io.advant.orm.DBConnection;
import io.advant.orm.test.service.ProductService;
import io.advant.orm.test.service.ServiceException;

/**
 * @author Marco Romagnolo
 */
public class TestService {

    private final ProductService productService;

    public TestService(DBConnection connection) {
        productService = new ProductService(connection);
    }

    public void insert() throws ServiceException {
        PrintUtil.test("Service - Insert");
        productService.insert();
    }

    public void update() throws ServiceException {
        PrintUtil.test("Service - Update");
        productService.update();
    }

    public void save() throws ServiceException {
        PrintUtil.test("Service - Save");
        productService.save();
    }

    public void find() throws ServiceException {
        PrintUtil.test("Service - Find");
        productService.find();
    }

    public void findAll() throws ServiceException {
        PrintUtil.test("Service - FindAll");
        productService.findAll();
    }

    public void delete() throws ServiceException {
        PrintUtil.test("Service - Delete");
        productService.delete();
    }

    public void deleteAll() throws ServiceException {
        PrintUtil.test("Service - DeleteAll");
        productService.deleteAll();
    }
}
