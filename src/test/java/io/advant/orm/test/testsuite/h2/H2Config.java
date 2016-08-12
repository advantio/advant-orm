package io.advant.orm.test.testsuite.h2;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class H2Config extends DefaultDBConfig {
    public H2Config() {
        super(DBType.H2, "mem:" + DefaultDBConfig.DATABASE + ";DB_CLOSE_DELAY=-1", null, null);
    }
}
