package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.DB;
import io.advant.orm.Params;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.AbstractTestDAO;
import io.advant.orm.test.testcase.DefaultEntities;
import io.advant.orm.test.testcase.DefaultHostParams;
import io.advant.orm.type.DBHostType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Marco on 29/07/2016.
 */
public class MysqlTestDAO extends AbstractTestDAO {

    @BeforeClass
    public void configure() throws ConnectionException {
        Params params = new DefaultHostParams(DBHostType.MYSQL, 3306);
        Connection connection = DB.newInstance(params, DefaultEntities.get()).getConnection();
        super.configure(connection);
    }

    @Test
    public void testDAO() throws OrmException {
        super.testDAO();
    }
}
