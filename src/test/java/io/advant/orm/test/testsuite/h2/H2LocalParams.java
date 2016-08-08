package io.advant.orm.test.testsuite.h2;

import io.advant.orm.DBLocalParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBLocalType;

/**
 * Created by Marco on 08/08/2016.
 */
public class H2LocalParams extends DBLocalParams {
    public H2LocalParams() {
        super(DBLocalType.H2, "mem:" + DefaultParams.DATABASE + ";DB_CLOSE_DELAY=-1", null, null);
    }
}
