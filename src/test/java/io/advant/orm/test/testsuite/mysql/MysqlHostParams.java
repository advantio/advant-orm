package io.advant.orm.test.testsuite.mysql;

import io.advant.orm.DBHostParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBType;

/**
 * Created by Marco on 08/08/2016.
 */
public class MysqlHostParams extends DBHostParams {

    public MysqlHostParams() {
        super(DBType.MYSQL, DefaultParams.HOST, 3306, DefaultParams.DATABASE, "root", "");
    }
}
