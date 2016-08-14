package io.advant.orm.examples.service;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.examples.dao.BrandDAO;
import io.advant.orm.examples.dao.impl.BrandDAOImpl;
import io.advant.orm.examples.entity.BrandEntity;
import io.advant.orm.examples.entity.CategoryEntity;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class BrandService {

    private static final Logger LOGGER = Logger.getLogger(BrandService.class.getName());
    private BrandDAO<BrandEntity> brandDAO;

    public BrandService() {
        try {
            DBConnection connection = DBFactory.getInstance().getConnection();
            brandDAO = new BrandDAOImpl(connection);
        } catch (ConnectionException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<BrandEntity> findAll() {
        try {
            return brandDAO.findAll();
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public void insert() {
        try {
            BrandEntity brand = new BrandEntity();
            brand.setName("TestBrand");
            brandDAO.insert(brand);
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
