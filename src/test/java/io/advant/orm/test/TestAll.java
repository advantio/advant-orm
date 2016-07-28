package io.advant.orm.test;

import io.advant.orm.DB;
import io.advant.orm.DBHostParams;
import io.advant.orm.DBLocalParams;
import io.advant.orm.Params;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;
import io.advant.orm.type.DBHostType;
import io.advant.orm.type.DBLocalType;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class TestAll {

    private Set<String> entities;
    private Map<String, Params> paramsMap;
    private static final String HOST = "localhost";
    private static final String DATABASE = "advant_orm";
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    public TestAll() {
        entities = new HashSet<>();
        entities.add(BrandEntity.class.getName());
        entities.add(CategoryEntity.class.getName());
        entities.add(ProductCategoryEntity.class.getName());
        entities.add(ProductEntity.class.getName());
        paramsMap = new HashMap<>();
        paramsMap.put("HSQL", new DBLocalParams(DBLocalType.HSQLDB, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("H2", new DBLocalParams(DBLocalType.H2, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("DERBY", new DBLocalParams(DBLocalType.DERBY, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("MYSQL", new DBHostParams(DBHostType.MYSQL, HOST, 3306, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("POSTGRESQL", new DBHostParams(DBHostType.POSTGRESQL, HOST, 5432, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("ORACLE", new DBHostParams(DBHostType.ORACLE, HOST, 1521, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("IBMDB2", new DBHostParams(DBHostType.IBMDB2, HOST, 50000, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("MSSQL", new DBHostParams(DBHostType.MSSQL, HOST, 1433, DATABASE, USER, PASSWORD, entities));
        paramsMap.put("SYBASE", new DBHostParams(DBHostType.SYBASE, HOST, 2638, DATABASE, USER, PASSWORD, entities));
    }

    public void start() {
        for (Map.Entry<String, Params> entry : paramsMap.entrySet()) {
            DB db = null;
            Params params = entry.getValue();
            String fileName = entry.getKey();
            try {
                db = DB.newInstance(params);
                create(fileName, db);
                launchTestSuite(db);
                drop(fileName, db);
            } catch (ConnectionException e) {
                System.out.println("No connection available for this database --> " + params.getUri() + "\n");
            } finally {
                if (db != null) {
                    try {
                        db.disconnect();
                    } catch (SQLException e) {
                        e.printStackTrace(System.out);
                    }
                }
            }
        }
    }

    private void create(String fileName, DB db) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + fileName + "create.sql");
        //TODO create DB from file
    }

    private void launchTestSuite(DB db) {
        try {
            TestSuite suite = new TestSuite(db);
        } catch (ConnectionException | OrmException e) {
            e.printStackTrace(System.out);
        }
    }

    private void drop(String fileName, DB db) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + fileName + "drop.sql");
        //TODO drop DB from file
    }
}
