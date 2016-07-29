package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.mssql.MSSQLCreateDB;
import io.advant.orm.test.testsuite.mssql.MSSQLDropDB;
import io.advant.orm.test.testsuite.mssql.MSSQLTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MSSQLCreateDB.class,
        MSSQLTestDAO.class,
        MSSQLDropDB.class
})
public class MSSQLSuite {
}
