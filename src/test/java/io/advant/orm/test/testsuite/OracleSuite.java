package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.oracle.OracleCreateDB;
import io.advant.orm.test.testsuite.oracle.OracleDropDB;
import io.advant.orm.test.testsuite.oracle.OracleTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        OracleCreateDB.class,
        OracleTestDAO.class,
        OracleDropDB.class
})
public class OracleSuite {
}
