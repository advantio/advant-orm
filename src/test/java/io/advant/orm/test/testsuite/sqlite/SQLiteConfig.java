package io.advant.orm.test.testsuite.sqlite;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class SQLiteConfig extends DefaultDBConfig {

    public SQLiteConfig() {
        super(DBType.SQLITE, DefaultDBConfig.DATABASE + ":memory", null, null);
    }

}
