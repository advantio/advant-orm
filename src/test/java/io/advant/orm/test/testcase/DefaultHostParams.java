package io.advant.orm.test.testcase;

import io.advant.orm.DB;
import io.advant.orm.DBHostParams;
import io.advant.orm.Params;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductCategoryEntity;
import io.advant.orm.test.entity.ProductEntity;
import io.advant.orm.type.DBHostType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marco on 29/07/2016.
 */
public class DefaultHostParams extends DBHostParams{

    public static final String HOST = "localhost";
    public static final String DATABASE = "advant_orm";
    public static final String USER = "test";
    public static final String PASSWORD = "test";


    public DefaultHostParams(DBHostType dbType, int port) {
        super(dbType, HOST, port, DATABASE, USER, PASSWORD);
    }
}
