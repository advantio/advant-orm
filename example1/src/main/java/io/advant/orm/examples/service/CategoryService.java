package io.advant.orm.examples.service;

import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.examples.dao.CategoryDAO;
import io.advant.orm.examples.dao.impl.CategoryDAOImpl;
import io.advant.orm.examples.entity.CategoryEntity;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class CategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryService.class.getName());
    private CategoryDAO<CategoryEntity> categoryDAO;

    public CategoryService() {
        try {
            DBConnection connection = DBFactory.getInstance().getConnection();
            categoryDAO = new CategoryDAOImpl(connection);
        } catch (ConnectionException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<CategoryEntity> findAll() {
        try {
            return categoryDAO.findAll();
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

}
