package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.h2.H2CreateTables;
import io.advant.orm.test.testsuite.h2.H2DropTables;
import io.advant.orm.test.testsuite.h2.H2TestDAO;
import io.advant.orm.test.testsuite.h2.H2TestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        H2CreateTables.class,
        H2TestDAO.class,
        H2TestService.class,
        H2DropTables.class
})
public class H2Suite {
}
