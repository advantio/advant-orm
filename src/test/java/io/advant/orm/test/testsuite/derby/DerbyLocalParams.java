package io.advant.orm.test.testsuite.derby;

import io.advant.orm.DBLocalParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBType;

/**
 * Created by Marco on 08/08/2016.
 */
public class DerbyLocalParams extends DBLocalParams {
    public DerbyLocalParams() {
        super(DBType.DERBY, "memory:" + DefaultParams.DATABASE + ";create=true;user=test;password=test", null, null);
    }
}
