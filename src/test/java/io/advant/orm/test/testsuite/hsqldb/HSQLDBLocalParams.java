package io.advant.orm.test.testsuite.hsqldb;

import io.advant.orm.DBLocalParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBLocalType;

/**
 * Created by Marco on 08/08/2016.
 */
public class HSQLDBLocalParams extends DBLocalParams {
    public HSQLDBLocalParams() {
        super(DBLocalType.HSQLDB, "mem:" + DefaultParams.DATABASE, null, null);
    }
}
