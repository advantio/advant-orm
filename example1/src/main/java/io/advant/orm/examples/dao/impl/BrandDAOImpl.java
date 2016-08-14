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

package io.advant.orm.examples.dao.impl;

import io.advant.orm.AbstractDAO;
import io.advant.orm.DBConnection;
import io.advant.orm.examples.dao.BrandDAO;
import io.advant.orm.exception.OrmException;
import io.advant.orm.internal.Condition;
import io.advant.orm.internal.Conditions;
import io.advant.orm.examples.entity.BrandEntity;

/**
 * Brand DAO Implementation
 *
 * @author Marco Romagnolo
 */
public class BrandDAOImpl extends AbstractDAO<BrandEntity> implements BrandDAO<BrandEntity> {

    public BrandDAOImpl(DBConnection connection) {
        super(connection);
    }

    @Override
    public BrandEntity findByName(String name) throws OrmException {
        Conditions conditions = new Conditions(new Condition(BrandEntity.class, "name", name));
        return find(conditions).get(0);
    }

}
