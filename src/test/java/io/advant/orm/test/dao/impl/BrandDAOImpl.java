package io.advant.orm.test.dao.impl;

import io.advant.orm.command.Condition;
import io.advant.orm.command.Where;
import io.advant.orm.dao.AbstractDAO;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.exception.DataException;
import io.advant.orm.test.dao.BrandDAO;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author Marco Romagnolo
 */
public class BrandDAOImpl extends AbstractDAO<BrandEntity> implements BrandDAO<BrandEntity> {

    public BrandDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public BrandEntity findByUserId(Integer userId) throws DataException {
        try {
            Where where = new Where(new Condition(BrandEntity.class, "userId", userId));
            ResultSet rs = select(where);
            return toEntity(rs);
        } catch (Exception e) {
            throw new DataException(e);
        }
    }

    @Override
    public BrandEntity findByUsername(String username) throws DataException {
        try {
            ;
            Where where = new Where(new Condition(BrandEntity.class, "username", username));
            ResultSet rs = select(where);
            return toEntity(rs);
        } catch (Exception e) {
            throw new DataException(e);
        }
    }

    @Override
    public BrandEntity findByEmail(String email) throws DataException {
        try {
            Where where = new Where(new Condition(BrandEntity.class, "email", email));
            ResultSet rs = select(where);
            return toEntity(rs);
        } catch (Exception e) {
            throw new DataException(e);
        }
    }

}
