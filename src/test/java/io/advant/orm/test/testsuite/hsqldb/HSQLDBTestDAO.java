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

package io.advant.orm.test.testsuite.hsqldb;

import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;
import io.advant.orm.test.testcase.PrintUtil;
import io.advant.orm.test.testcase.TestDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author Marco Romagnolo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HSQLDBTestDAO {

    private static TestDAO test;

    @BeforeClass
    public static void configure() throws ConnectionException {
        PrintUtil.suite(HSQLDBTestDAO.class.getName());
        DBConnection connection = DBFactory.newInstance(new HSQLDBConfig()).getConnection();
        test = new TestDAO(connection);
    }

    @Test
    public void test1_insert() throws OrmException {
        test.insert();
    }

    @Test
    public void test2_findAll() throws OrmException {
        test.findAll();
    }

    @Test
    public void test3_find() throws OrmException {
        test.find();
    }

    @Test
    public void test4_update() throws OrmException {
        test.update();
    }

    @Test
    public void test5_delete() throws OrmException {
        test.delete();
    }

    @Test
    public void test6_deleteAll() throws OrmException {
        test.deleteAll();
    }

    @AfterClass
    public static void disconnect() throws ConnectionException {
        DBFactory.getInstance().disconnect();
    }
}
