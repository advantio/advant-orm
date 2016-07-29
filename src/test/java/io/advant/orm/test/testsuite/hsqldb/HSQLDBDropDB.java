package io.advant.orm.test.testsuite.hsqldb;

import io.advant.orm.DB;
import io.advant.orm.Params;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.AbstractDropDB;
import io.advant.orm.test.testcase.DefaultEntities;
import io.advant.orm.test.testcase.DefaultHostParams;
import io.advant.orm.test.testcase.DefaultLocalParams;
import io.advant.orm.type.DBHostType;
import io.advant.orm.type.DBLocalType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class HSQLDBDropDB extends AbstractDropDB {

    private static Connection connection;

    @BeforeClass
    public static void connect() throws ConnectionException {
        Params params = new DefaultLocalParams(DBLocalType.HSQLDB);
        connection = DB.newInstance(params, DefaultEntities.get()).getConnection();
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        super.drop("MYSQL", connection);
    }
}
