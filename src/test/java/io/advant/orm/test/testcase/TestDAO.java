package io.advant.orm.test.testcase;

import io.advant.orm.GenericDAO;
import io.advant.orm.GenericDAOImpl;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.dao.BrandDAO;
import io.advant.orm.test.dao.ProductDAO;
import io.advant.orm.test.dao.impl.BrandDAOImpl;
import io.advant.orm.test.dao.impl.ProductDAOImpl;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;
import org.junit.Assert;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by Marco on 29/07/2016.
 */
public class TestDAO {

    private BrandDAO<BrandEntity> brandDAO;
    private ProductDAO<ProductEntity> productDAO;
    private GenericDAO<ProductCategoryEntity> productCategoryDAO;
    private GenericDAO<CategoryEntity> categoryDAO;

    public TestDAO(Connection connection) {
        brandDAO = new BrandDAOImpl(connection);
        productDAO = new ProductDAOImpl(connection);
        productCategoryDAO = new GenericDAOImpl<>(ProductCategoryEntity.class, connection);
        categoryDAO = new GenericDAOImpl<>(CategoryEntity.class, connection);
    }

    public void clear() throws OrmException {
        System.out.println(getClass().getName() + ": Clear tables");
        brandDAO.truncate(true);
        productDAO.truncate(true);
        productCategoryDAO.truncate(true);
        categoryDAO.truncate(true);
    }

    /**
     * Test Insert
     */
    public void insert() throws OrmException {
        System.out.println(getClass().getName() + ": Insert data");

        // Insert Brands

        BrandEntity brand = new BrandEntity();
        brand.setId(1000L);
        brand.setName("Brand");
        brandDAO.insert(brand);

        BrandEntity brand1 = new BrandEntity();
        brand1.setId(1001L);
        brand1.setName("Brand1");
        brandDAO.insert(brand1);
        System.out.println(getClass().getName() + ": Inserted brand: " + brand1);

        //Insert Categories

        CategoryEntity category = new CategoryEntity();
        category.setId(1000L);
        category.setName("Category name");
        category.setDescription("Category description");
        categoryDAO.insert(category);
        System.out.println(getClass().getName() + ": Inserted category: " + category);

        CategoryEntity category1 = new CategoryEntity();
        category1.setId(1001L);
        category1.setName("Category name 1");
        category1.setDescription("Category description 1");
        categoryDAO.insert(category1);
        System.out.println(getClass().getName() + ": Inserted category: " + category1);

        CategoryEntity category2 = new CategoryEntity();
        category2.setId(1002L);
        category2.setName("Category name 2");
        category2.setDescription("Category description 2");
        categoryDAO.insert(category2);
        System.out.println(getClass().getName() + ": Inserted category: " + category2);

        //Insert Products

        ProductEntity product = new ProductEntity();
        product.setId(1000L);
        product.setBrandId(brand.getId());
        //product.setBlocked(false);
        //product.setCreateDate(new Date());
        product.setName("Product name");
        product.setDescription("Product description");
        productDAO.insert(product);
        System.out.println(getClass().getName() + ": Inserted product: " + product);

        ProductEntity product1 = new ProductEntity();
        product1.setBrandId(brand.getId());
        product1.setBlocked(false);
        product1.setCreateDate(new Date());
        product1.setName("Product name 1");
        product1.setDescription("Product description 1");
        productDAO.insert(product1);
        System.out.println(getClass().getName() + ": Inserted product: " + product1);

        ProductEntity product2 = new ProductEntity();
        product2.setBrandId(brand.getId());
        product2.setBlocked(false);
        product2.setCreateDate(new Date());
        product2.setName("Product name 2");
        product2.setDescription("Product description 2");
        productDAO.insert(product2);
        System.out.println(getClass().getName() + ": Inserted product: " + product2);

        //Insert Product's Categories

        ProductCategoryEntity prodCat = new ProductCategoryEntity();
        prodCat.setCategoryId(category.getId());
        prodCat.setProductId(product.getId());
        productCategoryDAO.insert(prodCat);
        System.out.println(getClass().getName() + ": Inserted product's category: " + prodCat);

        ProductCategoryEntity prodCat1 = new ProductCategoryEntity();
        prodCat1.setCategoryId(category1.getId());
        prodCat1.setProductId(product.getId());
        productCategoryDAO.insert(prodCat1);
        System.out.println(getClass().getName() + ": Inserted product's category: " + prodCat1);

        ProductCategoryEntity prodCat2 = new ProductCategoryEntity();
        prodCat2.setCategoryId(category1.getId());
        prodCat2.setProductId(product1.getId());
        productCategoryDAO.insert(prodCat2);
        System.out.println(getClass().getName() + ": Inserted product's category: " + prodCat2);

        ProductCategoryEntity prodCat3 = new ProductCategoryEntity();
        prodCat3.setCategoryId(category2.getId());
        prodCat3.setProductId(product2.getId());
        productCategoryDAO.insert(prodCat3);
        System.out.println(getClass().getName() + ": Inserted product's category: " + prodCat3);
    }

    /**
     * Find test
     */
    public void find() throws OrmException {
        System.out.println("Find product...");
        ProductEntity product = productDAO.find(1000L);
        System.out.println(getClass().getName() + ": Found product: " + product);
        Assert.assertNotNull(product);
    }

    /**
     * FindAll test
     */
    public void findAll() throws OrmException {
        List<ProductEntity> products = productDAO.findAll();
        System.out.println(getClass().getName() + ": Found: " + products);
        Assert.assertNotNull(products);
    }

    /**
     * Update test
     */
    public void update() {
        System.out.println(getClass().getName() + ": Update data");
    }

    /**
     * Delete Test
     */
    public void delete() {
        System.out.println(getClass().getName() + ": Delete data");
    }
}