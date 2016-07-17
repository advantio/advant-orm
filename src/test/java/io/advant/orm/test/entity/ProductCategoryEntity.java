package io.advant.orm.test.entity;

import io.advant.orm.annotation.Relation;
import io.advant.orm.dao.Entity;
import io.advant.orm.test.table.ProductCategoryTable;

public class ProductCategoryEntity extends ProductCategoryTable implements Entity {

    @Relation(from = "categoryId" , to = "id")
    private CategoryEntity category;

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
