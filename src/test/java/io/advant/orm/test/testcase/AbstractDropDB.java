package io.advant.orm.test.testcase;

import io.advant.orm.Params;
import io.advant.orm.Query;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;

import java.io.InputStream;
import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public abstract class AbstractDropDB {

    public void drop(String fileName, Connection connection) throws ConnectionException, OrmException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + fileName + "drop.sql");
        Query.exec(connection, inputStream);
    }
}
