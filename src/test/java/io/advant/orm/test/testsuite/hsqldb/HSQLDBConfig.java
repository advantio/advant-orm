package io.advant.orm.test.testsuite.hsqldb;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class HSQLDBConfig extends DefaultDBConfig {
    public HSQLDBConfig() {
        super(DBType.HSQLDB, "mem:" + DefaultDBConfig.DATABASE, null, null);
    }
}
