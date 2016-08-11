package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.postgresql.PostgresqlCreateTables;
import io.advant.orm.test.testsuite.postgresql.PostgresqlDropTables;
import io.advant.orm.test.testsuite.postgresql.PostgresqlTestDAO;
import io.advant.orm.test.testsuite.postgresql.PostgresqlTestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostgresqlCreateTables.class,
        PostgresqlTestDAO.class,
        PostgresqlTestService.class,
        PostgresqlDropTables.class
})
public class PostgresqlSuite {
}
