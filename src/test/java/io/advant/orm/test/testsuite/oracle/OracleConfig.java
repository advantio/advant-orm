package io.advant.orm.test.testsuite.oracle;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class OracleConfig extends DefaultDBConfig {

    public OracleConfig() {
        super(DBType.ORACLE, DefaultDBConfig.HOST, 1521, "xe", DefaultDBConfig.USER, DefaultDBConfig.PASSWORD);
    }
}
