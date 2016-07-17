package io.advant.orm.test.entity;

import io.advant.orm.dao.Entity;
import io.advant.orm.test.table.ProductTable;
import io.advant.orm.annotation.Relation;

import java.util.List;

/**
 * @author Marco Romagnolo
 */
public class ProductEntity extends ProductTable implements Entity {

    @Relation(from = "brandId" , to = "id")
    private BrandEntity brand;

    @Relation(from = "id" , to = "productId")
    private List<ProductCategoryEntity> categories;

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public List<ProductCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategoryEntity> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "brand=" + brand +
                ", " + super.toString() +
                '}';
    }
}
