package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.sybase.SybaseCreateDB;
import io.advant.orm.test.testsuite.sybase.SybaseDropDB;
import io.advant.orm.test.testsuite.sybase.SybaseTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SybaseCreateDB.class,
        SybaseTestDAO.class,
        SybaseDropDB.class
})
public class SybaseSuite {
}
