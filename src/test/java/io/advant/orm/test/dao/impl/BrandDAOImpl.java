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

package io.advant.orm.test.dao.impl;

import io.advant.orm.internal.Condition;
import io.advant.orm.internal.Conditions;
import io.advant.orm.AbstractDAO;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.exception.DataException;
import io.advant.orm.test.dao.BrandDAO;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 */
public class BrandDAOImpl extends AbstractDAO<BrandEntity> implements BrandDAO<BrandEntity> {

    public BrandDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public BrandEntity findByUserId(Integer userId) throws DataException {
        try {
            Conditions conditions = new Conditions(new Condition(BrandEntity.class, "userId", userId));
            return find(conditions);
        } catch (Exception e) {
            throw new DataException(e);
        }
    }

    @Override
    public BrandEntity findByUsername(String username) throws DataException {
        try {
            Conditions conditions = new Conditions(new Condition(BrandEntity.class, "username", username));
            return find(conditions);
        } catch (Exception e) {
            throw new DataException(e);
        }
    }

    @Override
    public BrandEntity findByEmail(String email) throws DataException {
        try {
            Conditions conditions = new Conditions(new Condition(BrandEntity.class, "email", email));
            return find(conditions);
        } catch (Exception e) {
            throw new DataException(e);
        }
    }

}
