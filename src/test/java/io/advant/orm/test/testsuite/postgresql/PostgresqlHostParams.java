package io.advant.orm.test.testsuite.postgresql;

import io.advant.orm.DBHostParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBHostType;

/**
 * Created by Marco on 08/08/2016.
 */
public class PostgresqlHostParams extends DBHostParams {

    public PostgresqlHostParams() {
        super(DBHostType.MYSQL, DefaultParams.HOST, 5432, DefaultParams.DATABASE, "postgres", "");
    }
}
