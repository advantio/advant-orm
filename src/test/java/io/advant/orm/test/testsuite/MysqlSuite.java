package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.mysql.MysqlCreateTables;
import io.advant.orm.test.testsuite.mysql.MysqlDropTables;
import io.advant.orm.test.testsuite.mysql.MysqlTestDAO;
import io.advant.orm.test.testsuite.mysql.MysqlTestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MysqlCreateTables.class,
        MysqlTestDAO.class,
        MysqlTestService.class,
        MysqlDropTables.class
})
public class MysqlSuite {
}
