package io.advant.orm.test.testsuite.ibmdb2;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class IBMDB2Config extends DefaultDBConfig {

    public IBMDB2Config() {
        super(DBType.IBMDB2, DefaultDBConfig.HOST, 50000, DefaultDBConfig.HOST, DefaultDBConfig.USER, DefaultDBConfig.PASSWORD);
    }
}
