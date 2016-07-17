package io.advant.orm.test.dao.impl;

import io.advant.orm.dao.AbstractDAO;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.dao.CategoryDAO;

import java.sql.Connection;

/**
 * Created by Marco on 02/07/2016.
 */
public class CategoryDAOImpl extends AbstractDAO<CategoryEntity> implements CategoryDAO<CategoryEntity> {

    public CategoryDAOImpl(Connection connection) {
        super(connection);
    }

}
