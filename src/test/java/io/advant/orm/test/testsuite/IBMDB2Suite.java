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

package io.advant.orm.test.testsuite;

import io.advant.orm.test.testsuite.ibmdb2.IBMDB2CreateTables;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2DropTables;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2TestDAO;
import io.advant.orm.test.testsuite.ibmdb2.IBMDB2TestService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IBMDB2CreateTables.class,
        IBMDB2TestDAO.class,
        IBMDB2TestService.class,
        IBMDB2DropTables.class
})
public class IBMDB2Suite {
}
