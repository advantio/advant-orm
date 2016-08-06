package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.sqlite.SQLiteCreateDB;
import io.advant.orm.test.testsuite.sqlite.SQLiteDropDB;
import io.advant.orm.test.testsuite.sqlite.SQLiteTestDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SQLiteCreateDB.class,
        SQLiteTestDAO.class,
        SQLiteDropDB.class
})
public class SQLiteSuite {
}
