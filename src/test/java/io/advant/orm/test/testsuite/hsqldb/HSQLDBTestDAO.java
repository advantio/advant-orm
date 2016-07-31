package io.advant.orm.test.testsuite.hsqldb;

import io.advant.orm.DB;
import io.advant.orm.Params;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.TestDAO;
import io.advant.orm.test.testcase.DefaultEntities;
import io.advant.orm.test.testcase.DefaultLocalParams;
import io.advant.orm.type.DBLocalType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class HSQLDBTestDAO {

    private TestDAO test;

    @BeforeClass
    public void configure() throws ConnectionException {
        Params params = new DefaultLocalParams(DBLocalType.HSQLDB);
        Connection connection = DB.newInstance(params, DefaultEntities.get()).getConnection();
        test = new TestDAO(connection);
    }

    @Test
    public void testDAO() throws OrmException {
        test.testDAO();
    }
}
