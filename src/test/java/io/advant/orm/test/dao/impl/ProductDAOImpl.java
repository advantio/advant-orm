package io.advant.orm.test.dao.impl;

import io.advant.orm.dao.AbstractDAO;
import io.advant.orm.test.entity.ProductEntity;
import io.advant.orm.test.dao.ProductDAO;

import java.sql.Connection;

/**
 * @author Marco Romagnolo
 */
public class ProductDAOImpl extends AbstractDAO<ProductEntity> implements ProductDAO<ProductEntity> {

    public ProductDAOImpl(Connection connection) {
        super(connection);
    }

}
