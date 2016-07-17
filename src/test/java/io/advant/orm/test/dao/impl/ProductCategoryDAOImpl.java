package io.advant.orm.test.dao.impl;

import io.advant.orm.dao.AbstractDAO;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.dao.ProductCategoryDAO;

import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
public class ProductCategoryDAOImpl extends AbstractDAO<ProductCategoryEntity> implements ProductCategoryDAO<ProductCategoryEntity> {

    public ProductCategoryDAOImpl(Connection connection) {
        super(connection);
    }

}
