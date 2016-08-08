package io.advant.orm.test.testcase;

import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;

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

    public static Set<String> getEntities() {
        HashSet<String> entities = new HashSet<>();
        entities.add(BrandEntity.class.getName());
        entities.add(CategoryEntity.class.getName());
        entities.add(ProductCategoryEntity.class.getName());
        entities.add(ProductEntity.class.getName());
        return entities;
    }

}
