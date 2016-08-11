package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.derby.DerbyCreateTables;
import io.advant.orm.test.testsuite.derby.DerbyDropTables;
import io.advant.orm.test.testsuite.derby.DerbyTestDAO;
import io.advant.orm.test.testsuite.derby.DerbyTestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DerbyCreateTables.class,
        DerbyTestDAO.class,
        DerbyTestService.class,
        DerbyDropTables.class
})
public class DerbySuite {
}
