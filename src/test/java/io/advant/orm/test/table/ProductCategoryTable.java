package io.advant.orm.test.table;

import io.advant.orm.annotation.Column;
import io.advant.orm.annotation.Table;
import io.advant.orm.dao.AbstractTable;

@Table(name = "test_product_category")
public class ProductCategoryTable extends AbstractTable {

    @Column(name = "id")
    private Long id;

    @Column(name = "version")
    private Long version;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "category_id")
    private Long categoryId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductCategoryTable{" +
                "id=" + id +
                ", version=" + version +
                ", productId=" + productId +
                ", categoryId=" + categoryId +
                '}';
    }
}
