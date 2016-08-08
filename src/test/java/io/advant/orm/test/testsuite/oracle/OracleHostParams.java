package io.advant.orm.test.testsuite.oracle;

import io.advant.orm.DBHostParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBHostType;

/**
 * Created by Marco on 08/08/2016.
 */
public class OracleHostParams extends DBHostParams {

    public OracleHostParams() {
        super(DBHostType.ORACLE, DefaultParams.HOST, 1521, "xe", DefaultParams.USER, DefaultParams.PASSWORD);
    }
}
