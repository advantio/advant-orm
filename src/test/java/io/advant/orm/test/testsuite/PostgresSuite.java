package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.postgres.PostgresCreateDB;
import io.advant.orm.test.testsuite.postgres.PostgresDropDB;
import io.advant.orm.test.testsuite.postgres.PostgresTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostgresCreateDB.class,
        PostgresTestDAO.class,
        PostgresDropDB.class
})
public class PostgresSuite {
}
