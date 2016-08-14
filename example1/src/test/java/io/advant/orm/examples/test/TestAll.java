package io.advant.orm.examples.test;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.Query;
import io.advant.orm.examples.service.BrandService;
import io.advant.orm.examples.service.CategoryService;
import io.advant.orm.examples.service.ProductService;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.InputStream;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAll {

    private static String dbType = "HSQLDB";
    private static DBConnection connection;
    private static BrandService brandService;
    private static ProductService productService;
    private static CategoryService categoryService;

    @BeforeClass
    public static void connect() throws ConnectionException, OrmException {
        DB db = DBFactory.getInstance();
        connection = db.getConnection();
        Assert.assertTrue("Database is not connected", db.isConnected());
        brandService = new BrandService();
        productService = new ProductService();
        categoryService = new CategoryService();
        createTables(dbType);
    }

    public static void createTables(String fileName) throws OrmException {
        PrintUtil.test("Create tables");
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sql/" + fileName + "/create.sql");
        Query.run(connection, inputStream, true);
    }

    /**
     * Test Insert
     */
    @Test
    public void insert() {
        PrintUtil.test("Insert");
        brandService.insert();
//        productService.insert();
//        categoryService.insert();
    }

    /**
     * Find test
     */
    @Test
    public void find() {
        PrintUtil.test("Find");
//        brandService.find();
//        productService.find();
//        categoryService.find();
    }

    /**
     * FindAll test
     */
    @Test
    public void findAll() {
        PrintUtil.test("FindAll");
        brandService.findAll();
        productService.findAll();
        categoryService.findAll();
    }

    /**
     * Update test
     */
    @Test
    public void update() {
        PrintUtil.test("Update");
//        brandService.update();
//        productService.update();
//        categoryService.update();
    }

    /**
     * Delete Test
     */
    @Test
    public void delete() {
        PrintUtil.test("Delete");
//        brandService.delete();
//        productService.delete();
//        categoryService.delete();
    }

    /**
     * FindAll test
     */
    @Test
    public void deleteAll() {
        PrintUtil.test("DeleteAll");
//        brandService.deleteAll();
//        productService.deleteAll();
//        categoryService.deleteAll();
    }

    public static void dropTables(String fileName) throws OrmException {
        PrintUtil.test("Drop tables");
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sql/" + fileName + "/drop.sql");
        Query.run(connection, inputStream, true);
    }

    @AfterClass
    public static void disconnect() throws OrmException, ConnectionException {
        dropTables(dbType);
        DBFactory.getInstance().disconnect();
    }



}
