package io.advant.orm.test.testsuite.sybase;

import io.advant.orm.DB;
import io.advant.orm.Params;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.AbstractCreateDB;
import io.advant.orm.test.testcase.DefaultEntities;
import io.advant.orm.test.testcase.DefaultHostParams;
import io.advant.orm.type.DBHostType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class SybaseCreateDB extends AbstractCreateDB {

    private static Connection connection;

    @BeforeClass
    public static void connect() throws ConnectionException {
        Params params = new DefaultHostParams(DBHostType.SYBASE, 2638);
        connection = DB.newInstance(params, DefaultEntities.get()).getConnection();
    }

    @Test
    public void create() throws ConnectionException, OrmException {
        super.create("MYSQL", connection);
    }

}
