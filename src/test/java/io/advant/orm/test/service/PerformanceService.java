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

package io.advant.orm.test.service;

import io.advant.orm.DBConnection;
import io.advant.orm.test.dao.BrandDAO;
import io.advant.orm.test.dao.CategoryDAO;
import io.advant.orm.test.dao.ProductDAO;
import io.advant.orm.test.dao.impl.BrandDAOImpl;
import io.advant.orm.test.dao.impl.CategoryDAOImpl;
import io.advant.orm.test.dao.impl.ProductDAOImpl;
import io.advant.orm.test.entity.BrandEntity;
import io.advant.orm.test.entity.CategoryEntity;
import io.advant.orm.test.entity.ProductEntity;

import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class PerformanceService {

    private static final Logger LOGGER = Logger.getLogger(PerformanceService.class.getName());
    private BrandDAO<BrandEntity> brandDAO;
    private CategoryDAO<CategoryEntity> categoryDAO;
    private ProductDAO<ProductEntity> productDAO;

    public PerformanceService(DBConnection connection) {
        brandDAO = new BrandDAOImpl(connection);
        categoryDAO = new CategoryDAOImpl(connection);
        productDAO = new ProductDAOImpl(connection);
    }

    public void test1() throws ServiceException {

    }

}
