package io.advant.orm.test.testsuite;

import io.advant.orm.test.testcase.AbstractTestDAO;
import io.advant.orm.test.testsuite.derby.DerbyCreateDB;
import io.advant.orm.test.testsuite.derby.DerbyDropDB;
import io.advant.orm.test.testsuite.derby.DerbyTestDAO;
import io.advant.orm.test.testsuite.mysql.MysqlCreateDB;
import io.advant.orm.test.testsuite.mysql.MysqlDropDB;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DerbyCreateDB.class,
        DerbyTestDAO.class,
        DerbyDropDB.class
})
public class DerbySuite {
}
