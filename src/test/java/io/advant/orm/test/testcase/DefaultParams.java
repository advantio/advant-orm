package io.advant.orm.test.testcase;

import io.advant.orm.DBHostParams;
import io.advant.orm.DBLocalParams;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;
import io.advant.orm.type.DBHostType;
import io.advant.orm.type.DBLocalType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marco on 01/08/2016.
 */
public class DefaultParams {

    public static final String HOST = "localhost";
    public static final String DATABASE = "advantorm";
    public static final String USER = "advantorm";
    public static final String PASSWORD = "advantorm";

    private String database;
    private HashSet<String> entities;

    public DefaultParams(String database) {
        this();
        this.database = database;
    }

    public DefaultParams() {
        entities = new HashSet<>();
        entities.add(BrandEntity.class.getName());
        entities.add(CategoryEntity.class.getName());
        entities.add(ProductCategoryEntity.class.getName());
        entities.add(ProductEntity.class.getName());
        database = DATABASE;
    }

    public Set<String> getEntities() {
        return entities;
    }

    public DBLocalParams getDBLocalParams(DBLocalType dbType) {
        return new DBLocalParams(dbType, database, USER, PASSWORD);
    }

    public DBHostParams getDBHostParams(DBHostType dbType, int port) {
        return new DBHostParams(dbType, HOST, port, database, USER, PASSWORD);
    }

}
