package io.advant.orm.test.testsuite.postgresql;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class PostgresqlConfig extends DefaultDBConfig {

    public PostgresqlConfig() {
        super(DBType.POSTGRESQL, DefaultDBConfig.HOST, 5432, DefaultDBConfig.DATABASE, "postgres", "");
    }
}
