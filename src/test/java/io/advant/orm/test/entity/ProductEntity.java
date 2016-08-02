package io.advant.orm.test.entity;

import io.advant.orm.Entity;
import io.advant.orm.annotation.Relation;
import io.advant.orm.test.table.ProductTable;

import java.util.List;

/**
 * Copyright 2016 Advant I/O
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
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
        return "ProductEntity{" +
                "brand=" + brand +
                ", " + super.toString() +
                '}';
    }
}
