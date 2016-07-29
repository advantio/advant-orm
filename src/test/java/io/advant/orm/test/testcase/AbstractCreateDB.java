package io.advant.orm.test.testcase;

import io.advant.orm.Params;
import io.advant.orm.Query;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;

import java.io.InputStream;
import java.sql.Connection;

/**
 *
 */
public abstract class AbstractCreateDB {

    public void create(String fileName, Connection connection) throws ConnectionException, OrmException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + fileName + "create.sql");
        Query.exec(connection, inputStream);
    }
}
