package io.advant.orm.test;

import io.advant.orm.Database;
import io.advant.orm.Params;
import io.advant.orm.dao.GenericDAO;
import io.advant.orm.dao.GenericDAOImpl;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.dao.BrandDAO;
import io.advant.orm.test.dao.ProductDAO;
import io.advant.orm.test.dao.impl.BrandDAOImpl;
import io.advant.orm.test.dao.impl.ProductDAOImpl;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;
import io.advant.orm.type.DBType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Marco Romagnolo
 */
public class OrmTest {

    private BrandDAO<BrandEntity> brandDAO;
    private ProductDAO<ProductEntity> productDAO;
    private GenericDAO<ProductCategoryEntity> productCategoryDAO;
    private GenericDAO<CategoryEntity> categoryDAO;

    private Properties getProperties(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        Properties config = new Properties();
        if (inputStream != null) {
            config.load(inputStream);
        }
        return config;
    }

    /**
     * Launch all test in order
     */
    @Test
    public void launchAll() throws Exception {
        List<Properties> list = new ArrayList<>();
        list.add(getProperties("postgresql.properties"));
        list.add(getProperties("mysql.properties"));
        list.add(getProperties("oracle.properties"));
        list.add(getProperties("mssql.properties"));
        list.add(getProperties("ibmdb2.properties"));
        for (Properties prop : list) {
            prop.list(System.out);

            String host = prop.getProperty("host");
            int port = Integer.valueOf(prop.getProperty("port"));
            String database = prop.getProperty("database");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            String dbtype = prop.getProperty("dbtype");
            Set<String> entities = new HashSet<>();
            entities.add(BrandEntity.class.getName());
            entities.add(CategoryEntity.class.getName());
            entities.add(ProductCategoryEntity.class.getName());
            entities.add(ProductEntity.class.getName());
            Params conf = new Params(DBType.valueOf(dbtype.toUpperCase()), host, port, database, user, password, entities);

            Database db = null;
            try {
                db = Database.newInstance(conf);
                configure(db.getConnection());
                launch();
            } catch (ConnectionException e) {
                System.out.println("No connection available for this database: " + conf + "\n");
            } finally {
                if (db != null) {
                    try {
                        db.disconnect();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void launch() throws OrmException {
        brandDAO.clearTable();
        productDAO.clearTable();
        productCategoryDAO.clearTable();
        categoryDAO.clearTable();
        insert();
        find();
        findAll();
        update();
        delete();
    }

    private void configure(Connection connection) {
        brandDAO = new BrandDAOImpl(connection);
        productDAO = new ProductDAOImpl(connection);
        productCategoryDAO = new GenericDAOImpl<>(ProductCategoryEntity.class, connection);
        categoryDAO = new GenericDAOImpl<>(CategoryEntity.class, connection);
    }

    /**
     * Test Insert
     */
    public void insert() throws OrmException {
        System.out.println("Inserting data...");

        // Insert Brands

        BrandEntity brand = new BrandEntity();
        brand.setId(1000L);
        brand.setName("Brand");
        brandDAO.insert(brand);
        System.out.println("Inserted brand: " + brand);

        BrandEntity brand1 = new BrandEntity();
        brand1.setId(1001L);
        brand1.setName("Brand1");
        brandDAO.insert(brand1);
        System.out.println("Inserted brand: " + brand1);

        //Insert Categories

        CategoryEntity category = new CategoryEntity();
        category.setId(1000L);
        category.setName("Category name");
        category.setDescription("Category description");
        categoryDAO.insert(category);
        System.out.println("Inserted category: " + category);

        CategoryEntity category1 = new CategoryEntity();
        category1.setId(1001L);
        category1.setName("Category name 1");
        category1.setDescription("Category description 1");
        categoryDAO.insert(category1);
        System.out.println("Inserted category: " + category1);

        CategoryEntity category2 = new CategoryEntity();
        category2.setId(1002L);
        category2.setName("Category name 2");
        category2.setDescription("Category description 2");
        categoryDAO.insert(category2);
        System.out.println("Inserted category: " + category2);

        //Insert Products

        ProductEntity product = new ProductEntity();
        product.setId(1000L);
        product.setBrandId(brand.getId());
        product.setBlocked(false);
        product.setCreateDate(new Date());
        product.setName("Product name");
        product.setDescription("Product description");
        productDAO.insert(product);
        System.out.println("Inserted product: " + product);

        ProductEntity product1 = new ProductEntity();
        product1.setBrandId(brand.getId());
        product1.setBlocked(false);
        product1.setCreateDate(new Date());
        product1.setName("Product name 1");
        product1.setDescription("Product description 1");
        productDAO.insert(product1);
        System.out.println("Inserted product: " + product1);

        ProductEntity product2 = new ProductEntity();
        product2.setBrandId(brand.getId());
        product2.setBlocked(false);
        product2.setCreateDate(new Date());
        product2.setName("Product name 2");
        product2.setDescription("Product description 2");
        productDAO.insert(product2);
        System.out.println("Inserted product: " + product2);

        //Insert Product's Categories

        ProductCategoryEntity prodCat = new ProductCategoryEntity();
        prodCat.setCategoryId(category.getId());
        prodCat.setProductId(product.getId());
        productCategoryDAO.insert(prodCat);
        System.out.println("Inserted product's category: " + prodCat);

        ProductCategoryEntity prodCat1 = new ProductCategoryEntity();
        prodCat1.setCategoryId(category1.getId());
        prodCat1.setProductId(product.getId());
        productCategoryDAO.insert(prodCat1);
        System.out.println("Inserted product's category: " + prodCat1);

        ProductCategoryEntity prodCat2 = new ProductCategoryEntity();
        prodCat2.setCategoryId(category1.getId());
        prodCat2.setProductId(product1.getId());
        productCategoryDAO.insert(prodCat2);
        System.out.println("Inserted product's category: " + prodCat2);

        ProductCategoryEntity prodCat3 = new ProductCategoryEntity();
        prodCat3.setCategoryId(category2.getId());
        prodCat3.setProductId(product2.getId());
        productCategoryDAO.insert(prodCat3);
        System.out.println("Inserted product's category: " + prodCat3);
    }

    /**
     * Find test
     */
    public void find() throws OrmException {
        System.out.println("Find product...");
        ProductEntity product = productDAO.find(1000L);
        System.out.println("Found product: " + product);
        Assert.assertNotNull(product);
    }

    /**
     * FindAll test
     */
    private void findAll() throws OrmException {
        List<ProductEntity> products = productDAO.findAll();
        System.out.println("Found: " + products);
        Assert.assertNotNull(products);
    }

    /**
     * Update test
     */
    private void update() {

    }

    /**
     * Delete Test
     */
    private void delete() {

    }
}
