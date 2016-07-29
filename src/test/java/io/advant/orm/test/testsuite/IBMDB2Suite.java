package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.ibmdb2.IBMDB2CreateDB;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2DropDB;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2TestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IBMDB2CreateDB.class,
        IBMDB2TestDAO.class,
        IBMDB2DropDB.class
})
public class IBMDB2Suite {
}
