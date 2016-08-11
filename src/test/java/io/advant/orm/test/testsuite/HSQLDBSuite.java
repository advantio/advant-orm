package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.hsqldb.HSQLDBCreateTables;
import io.advant.orm.test.testsuite.hsqldb.HSQLDBDropTables;
import io.advant.orm.test.testsuite.hsqldb.HSQLDBTestDAO;
import io.advant.orm.test.testsuite.hsqldb.HSQLDBTestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HSQLDBCreateTables.class,
        HSQLDBTestDAO.class,
        HSQLDBTestService.class,
        HSQLDBDropTables.class
})
public class HSQLDBSuite {
}
