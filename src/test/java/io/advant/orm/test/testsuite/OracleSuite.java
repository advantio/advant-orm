package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.oracle.OracleCreateTables;
import io.advant.orm.test.testsuite.oracle.OracleDropTables;
import io.advant.orm.test.testsuite.oracle.OracleTestDAO;
import io.advant.orm.test.testsuite.oracle.OracleTestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        OracleCreateTables.class,
        OracleTestDAO.class,
        OracleTestService.class,
        OracleDropTables.class
})
public class OracleSuite {
}
