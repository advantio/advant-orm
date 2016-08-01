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

package io.advant.orm.test.dao;

import io.advant.orm.DAO;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.exception.DataException;

/**
 *
 * @param <T>
 */
public interface BrandDAO<T> extends DAO<T> {

    BrandEntity findByUserId(Integer userId) throws DataException;

    BrandEntity findByUsername(String username) throws DataException;

    BrandEntity findByEmail(String email) throws DataException;

}
