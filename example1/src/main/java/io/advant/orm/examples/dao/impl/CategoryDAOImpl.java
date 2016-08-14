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
import io.advant.orm.examples.dao.CategoryDAO;
import io.advant.orm.examples.entity.CategoryEntity;

/**
 *
 */
public class CategoryDAOImpl extends AbstractDAO<CategoryEntity> implements CategoryDAO<CategoryEntity> {

    public CategoryDAOImpl(DBConnection connection) {
        super(connection);
    }

}
