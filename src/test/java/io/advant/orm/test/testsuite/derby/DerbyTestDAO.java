package io.advant.orm.test.testsuite.derby;

import io.advant.orm.DB;
import io.advant.orm.DBLocalParams;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.test.testcase.TestDAO;
import io.advant.orm.type.DBLocalType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Marco Romagnolo
 */
public class DerbyTestDAO {

    private static TestDAO test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        DefaultParams defaultParams = new DefaultParams("memory:" + DefaultParams.DATABASE + ";create=true;user=test;password=test");
        DBLocalParams params = defaultParams.getDBLocalParams(DBLocalType.DERBY);
        Connection connection = DB.newInstance(params, defaultParams.getEntities()).getConnection();
        test = new TestDAO(connection);
    }

    @Test
    public void testDAO() throws OrmException {
        test.clear();
        test.insert();
        test.find();
        test.findAll();
        test.update();
        test.delete();
    }

    @AfterClass
    public static void disconnect() throws ConnectionException, SQLException {
        DB.getInstance().disconnect();
    }
}
