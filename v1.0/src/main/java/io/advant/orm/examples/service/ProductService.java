package io.advant.orm.examples.service;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.examples.dao.CategoryDAO;
import io.advant.orm.examples.dao.ProductDAO;
import io.advant.orm.examples.dao.impl.CategoryDAOImpl;
import io.advant.orm.examples.dao.impl.ProductDAOImpl;
import io.advant.orm.examples.entity.CategoryEntity;
import io.advant.orm.examples.entity.ProductEntity;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());
    private ProductDAO<ProductEntity> productDAO;

    public ProductService() {
        try {
            DBConnection connection = DBFactory.getInstance().getConnection();
            productDAO = new ProductDAOImpl(connection);
        } catch (ConnectionException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<ProductEntity> findAll() {
        try {
            return productDAO.findAll();
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

}
