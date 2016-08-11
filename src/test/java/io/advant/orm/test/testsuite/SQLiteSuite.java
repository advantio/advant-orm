package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.sqlite.SQLiteCreateTables;
import io.advant.orm.test.testsuite.sqlite.SQLiteDropTables;
import io.advant.orm.test.testsuite.sqlite.SQLiteTestDAO;
import io.advant.orm.test.testsuite.sqlite.SQLiteTestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SQLiteCreateTables.class,
        SQLiteTestDAO.class,
        SQLiteTestService.class,
        SQLiteDropTables.class
})
public class SQLiteSuite {
}
