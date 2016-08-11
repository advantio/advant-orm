package io.advant.orm.test.service;

import io.advant.orm.DBConnection;
import io.advant.orm.test.dao.BrandDAO;
import io.advant.orm.test.dao.CategoryDAO;
import io.advant.orm.test.dao.ProductDAO;
import io.advant.orm.test.dao.impl.BrandDAOImpl;
import io.advant.orm.test.dao.impl.CategoryDAOImpl;
import io.advant.orm.test.dao.impl.ProductDAOImpl;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductEntity;

import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class PerformanceService {

    private static final Logger LOGGER = Logger.getLogger(PerformanceService.class.getName());
    private BrandDAO<BrandEntity> brandDAO;
    private CategoryDAO<CategoryEntity> categoryDAO;
    private ProductDAO<ProductEntity> productDAO;

    public PerformanceService(DBConnection connection) {
        brandDAO = new BrandDAOImpl(connection);
        categoryDAO = new CategoryDAOImpl(connection);
        productDAO = new ProductDAOImpl(connection);
    }

    public void test1() throws ServiceException {

    }

}
