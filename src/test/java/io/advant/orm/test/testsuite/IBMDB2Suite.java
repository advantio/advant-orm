package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.ibmdb2.IBMDB2CreateTables;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2DropTables;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2TestDAO;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2TestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IBMDB2CreateTables.class,
        IBMDB2TestDAO.class,
        IBMDB2TestService.class,
        IBMDB2DropTables.class
})
public class IBMDB2Suite {
}
