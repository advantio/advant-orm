package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.test.testcase.DefaultDBConfig;
import io.advant.orm.type.DBType;

/**
 * @author Marco Romagnolo
 */
public class MysqlConfig extends DefaultDBConfig {

    public MysqlConfig() {
        super(DBType.MYSQL, DefaultDBConfig.HOST, 3306, DefaultDBConfig.DATABASE, "root", "");
    }
}
