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

package io.advant.orm.examples.test;

import io.advant.orm.DB;
import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.Query;
import io.advant.orm.examples.exception.ServiceException;
import io.advant.orm.examples.service.StoreService;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.InputStream;

/**
 * Test Application functionality
 *
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestApplication {

    private static String dbType = "HSQLDB";
    private static DBConnection connection;
    private static StoreService storeService;

    @BeforeClass
    public static void connect() throws ServiceException, ConnectionException, OrmException {
        DB db = DBFactory.getInstance();
        storeService = new StoreService();
        connection = db.getConnection();
        Assert.assertTrue("Database is not connected", db.isConnected());
        createTables(dbType);
    }

    public static void createTables(String fileName) throws OrmException {
        PrintUtil.test("Create tables");
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sql/" + fileName + "/create.sql");
        Query.run(connection, inputStream, true);
    }

    /**
     * Test Add Product
     */
    @Test
    public void test1_addProduct() throws ServiceException {
        PrintUtil.test("Add a Product to Store");
        storeService.addProduct("Apple", "iPhone 6", "3D Touch Camera Technology", "Smartphone");
    }

    /**
     * Test Find Brand by Name
     */
    @Test
    public void test2_findBrandName() throws ServiceException {
        PrintUtil.test("Find by Brand Name");
        Long brandId = storeService.findBrandByName("Apple");
        Assert.assertTrue(brandId != null);
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
