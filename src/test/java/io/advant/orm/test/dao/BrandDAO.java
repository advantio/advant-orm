package io.advant.orm.test.dao;

import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.exception.DataException;
import io.advant.orm.dao.DAO;

/**
 * @author Marco Romagnolo
 */
public interface BrandDAO<T> extends DAO<T> {

    BrandEntity findByUserId(Integer userId) throws DataException;

    BrandEntity findByUsername(String username) throws DataException;

    BrandEntity findByEmail(String email) throws DataException;

}
