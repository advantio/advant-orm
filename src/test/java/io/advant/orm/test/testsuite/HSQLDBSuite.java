package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.derby.DerbyCreateDB;
import io.advant.orm.test.testsuite.derby.DerbyDropDB;
import io.advant.orm.test.testsuite.derby.DerbyTestDAO;
import io.advant.orm.test.testsuite.hsqldb.HSQLDBCreateDB;
import io.advant.orm.test.testsuite.hsqldb.HSQLDBDropDB;
import io.advant.orm.test.testsuite.hsqldb.HSQLDBTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HSQLDBCreateDB.class,
        HSQLDBTestDAO.class,
        HSQLDBDropDB.class
})
public class HSQLDBSuite {
}
