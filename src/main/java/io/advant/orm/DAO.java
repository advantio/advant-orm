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

package io.advant.orm;

import io.advant.orm.exception.OrmException;
import io.advant.orm.internal.Conditions;

import java.util.List;

public interface DAO<T> {

    int deleteAll() throws OrmException;

    void insert(T entity) throws OrmException;

    int update(T entity) throws OrmException;

    Integer save(T object) throws OrmException;

    int delete(T object) throws OrmException;

    int delete(Conditions conditions) throws OrmException;

    List<T> findAll() throws OrmException;

    T find(Long id) throws OrmException;

    List<T> find(Conditions conditions) throws OrmException;

    boolean isAutoCommit() throws OrmException;

    void setAutoCommit(boolean autoCommit) throws OrmException;

    void commit() throws OrmException;

    void rollback();
}
