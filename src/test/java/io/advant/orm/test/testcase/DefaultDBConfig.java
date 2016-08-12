package io.advant.orm.test.testcase;

import io.advant.orm.DBConfig;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;
import io.advant.orm.type.DBType;

import java.util.HashSet;

/**
 * Created by Marco on 01/08/2016.
 */
public class DefaultDBConfig extends DBConfig {

    public static final String HOST = "localhost";
    public static final String DATABASE = "advantorm";
    public static final String USER = "advantorm";
    public static final String PASSWORD = "advantorm";

    public DefaultDBConfig(DBType dbType, String database, String user, String password) {
        super(dbType, database, user, password);
        configure();
    }

    public DefaultDBConfig(DBType dbType, String host, int port, String database, String user, String password) {
        super(dbType, host, port, database, user, password);
        configure();
    }

    private void configure() {
        HashSet<String> entities = new HashSet<>();
        entities.add(BrandEntity.class.getName());
        entities.add(CategoryEntity.class.getName());
        entities.add(ProductCategoryEntity.class.getName());
        entities.add(ProductEntity.class.getName());
        setEntities(entities);
    }

}
