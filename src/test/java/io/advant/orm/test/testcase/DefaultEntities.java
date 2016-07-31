package io.advant.orm.test.testcase;

import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marco on 29/07/2016.
 */
public class DefaultEntities {

    public static Set<String> get() {
        HashSet<String> entities = new HashSet<>();
        entities.add(BrandEntity.class.getName());
        entities.add(CategoryEntity.class.getName());
        entities.add(ProductCategoryEntity.class.getName());
        entities.add(ProductEntity.class.getName());
        return entities;
    }


}
