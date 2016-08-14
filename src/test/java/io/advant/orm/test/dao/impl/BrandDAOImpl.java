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

import io.advant.orm.AbstractDAO;
import io.advant.orm.DBConnection;
import io.advant.orm.exception.OrmException;
import io.advant.orm.internal.Condition;
import io.advant.orm.internal.Conditions;
import io.advant.orm.test.dao.BrandDAO;
import io.advant.orm.test.entity.BrandEntity;

import java.util.List;

/**
 * @author Marco Romagnolo
 */
public class BrandDAOImpl extends AbstractDAO<BrandEntity> implements BrandDAO<BrandEntity> {

    public BrandDAOImpl(DBConnection connection) {
        super(connection);
    }

    @Override
    public BrandEntity findByUserId(Integer userId) throws OrmException {
        try {
            Conditions conditions = new Conditions(new Condition(BrandEntity.class, "userId", userId));
            List<BrandEntity> entities = find(conditions);
            return entities.get(1);
        } catch (Exception e) {
            throw new OrmException(e);
        }
    }

    @Override
    public BrandEntity findByUsername(String username) throws OrmException {
        try {
            Conditions conditions = new Conditions(new Condition(BrandEntity.class, "username", username));
            List<BrandEntity> entities = find(conditions);
            return entities.get(1);
        } catch (Exception e) {
            throw new OrmException(e);
        }
    }

    @Override
    public BrandEntity findByEmail(String email) throws OrmException {
        try {
            Conditions conditions = new Conditions(new Condition(BrandEntity.class, "email", email));
            List<BrandEntity> entities = find(conditions);
            return entities.get(1);
        } catch (Exception e) {
            throw new OrmException(e);
        }
    }

}
