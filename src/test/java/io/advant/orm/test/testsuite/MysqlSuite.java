package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.mysql.MysqlCreateDB;
import io.advant.orm.test.testsuite.mysql.MysqlDropDB;
import io.advant.orm.test.testsuite.mysql.MysqlTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MysqlCreateDB.class,
        MysqlTestDAO.class,
        MysqlDropDB.class
})
public class MysqlSuite {
}
