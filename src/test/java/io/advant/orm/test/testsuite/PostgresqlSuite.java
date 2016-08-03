package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.postgresql.PostgresqlCreateDB;
import io.advant.orm.test.testsuite.postgresql.PostgresqlDropDB;
import io.advant.orm.test.testsuite.postgresql.PostgresqlTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostgresqlCreateDB.class,
        PostgresqlTestDAO.class,
        PostgresqlDropDB.class
})
public class PostgresqlSuite {
}
