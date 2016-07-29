package io.advant.orm.test.testcase;

import io.advant.orm.DBLocalParams;
import io.advant.orm.type.DBLocalType;

/**
 * Created by Marco on 29/07/2016.
 */
public class DefaultLocalParams extends DBLocalParams {

    public static final String DATABASE = "advant_orm";
    public static final String USER = "test";
    public static final String PASSWORD = "test";

    public DefaultLocalParams(DBLocalType dbType) {
        super(dbType, DATABASE, USER, PASSWORD);
    }
}
