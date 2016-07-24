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

package io.advant.orm.type;

/**
 *
 */
public enum DBType {
    MYSQL("com.mysql.jdbc.Driver"),
    POSTGRESQL("org.postgresql.Driver"),
    IBMDB2("COM.ibm.db2.jdbc.app.DB2Driver"),
    MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDrive"),
    ORACLE("oracle.jdbc.driver.OracleDriver");

    private final String driver;

    DBType(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}