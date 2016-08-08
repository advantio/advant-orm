package io.advant.orm.test.testsuite.ibmdb2;

import io.advant.orm.DBHostParams;
import io.advant.orm.test.testcase.DefaultParams;
import io.advant.orm.type.DBHostType;

/**
 * Created by Marco on 08/08/2016.
 */
public class IBMDB2HostParams extends DBHostParams {

    public IBMDB2HostParams() {
        super(DBHostType.IBMDB2, DefaultParams.HOST, 50000, DefaultParams.HOST, DefaultParams.USER, DefaultParams.PASSWORD);
    }
}
