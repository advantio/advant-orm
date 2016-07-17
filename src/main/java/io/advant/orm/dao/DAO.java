package io.advant.orm.dao;

import io.advant.orm.exception.OrmException;

import java.util.List;

/**
 * @author Marco Romagnolo
 */
public interface DAO<T> {

    void close() throws OrmException;

    void insert(T entity) throws OrmException;

    int update(T entity) throws OrmException;

    T save(T object) throws OrmException;

    void delete(T object) throws OrmException;

    List<T> findAll() throws OrmException;

    T find(Long id) throws OrmException;

    void clearTable() throws OrmException;
}
