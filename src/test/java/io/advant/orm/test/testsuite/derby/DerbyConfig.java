package io.advant.orm.test.testsuite.derby;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class DerbyConfig extends DefaultDBConfig {
    public DerbyConfig() {
        super(DBType.DERBY, "memory:" + DefaultDBConfig.DATABASE + ";create=true;user=test;password=test", null, null);
    }
}
