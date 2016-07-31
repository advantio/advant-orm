package io.advant.orm.test.testsuite.ibmdb2;

import io.advant.orm.DB;
import io.advant.orm.Params;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.TestDAO;
import io.advant.orm.test.testcase.DefaultEntities;
import io.advant.orm.test.testcase.DefaultHostParams;
import io.advant.orm.type.DBHostType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class IBMDB2TestDAO {

    private TestDAO test;

    @BeforeClass
    public void configure() throws ConnectionException {
        Params params = new DefaultHostParams(DBHostType.IBMDB2, 50000);
        Connection connection = DB.newInstance(params, DefaultEntities.get()).getConnection();
        test = new TestDAO(connection);
    }

    @Test
    public void testDAO() throws OrmException {
        test.testDAO();
    }
}
