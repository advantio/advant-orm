package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.h2.H2CreateDB;
import io.advant.orm.test.testsuite.h2.H2DropDB;
import io.advant.orm.test.testsuite.h2.H2TestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        H2CreateDB.class,
        H2TestDAO.class,
        H2DropDB.class
})
public class H2Suite {
}
