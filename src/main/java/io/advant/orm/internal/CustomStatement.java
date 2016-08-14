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

package io.advant.orm.internal;

import io.advant.orm.DBConnection;
import io.advant.orm.type.DBType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Marco Romagnolo
 */
public class CustomStatement {

    private final DBConnection connection;
    private DBType dbType;

    public CustomStatement(DBConnection connection) {
        this.connection = connection;
        this.dbType = connection.getDbType();
    }

    public PreparedStatement forInsert(String sql) throws SQLException {
        String[] ids;
        if (dbType == DBType.POSTGRESQL) {
            ids = new String[]{"id"};
        } else {
            ids = new String[]{"ID"};
        }
        return connection.prepareStatement(sql, ids);
    }


}
