package io.advant.orm.test.testsuite.sqlite;

import io.advant.orm.DBLocalParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBType;

/**
 * Created by Marco on 08/08/2016.
 */
public class SQLiteLocalParams extends DBLocalParams {
    public SQLiteLocalParams() {
        super(DBType.SQLITE, DefaultParams.DATABASE + ":memory", null, null);
    }
}
