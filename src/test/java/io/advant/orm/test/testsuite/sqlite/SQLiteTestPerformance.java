/**
 * Copyright 2016 Advant I/O
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.advant.orm.test.testsuite.sqlite;

import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.test.service.ServiceException;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestPerformance;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SQLiteTestPerformance {

    private static TestPerformance test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(SQLiteTestPerformance.class.getName());
        DBConnection connection = DBFactory.newInstance(new SQLiteConfig()).getConnection();
        test = new TestPerformance(connection);
    }

    @Test
    public void test1() throws ServiceException {
        test.test1();
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DBFactory.getInstance().disconnect();
    }
}
