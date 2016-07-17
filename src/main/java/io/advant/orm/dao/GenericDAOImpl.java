package io.advant.orm.dao;

import java.sql.Connection;

/**
 * Created by francesca on 17/07/16.
 */
public class GenericDAOImpl<T extends Entity> extends AbstractDAO<T> implements GenericDAO<T> {

    public GenericDAOImpl(Class<T> entityClass, Connection connection) {
        super(entityClass, connection);
    }

}
