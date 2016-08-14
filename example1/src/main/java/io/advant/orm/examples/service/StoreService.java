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

package io.advant.orm.examples.service;

import io.advant.orm.DBConnection;
import io.advant.orm.DBFactory;
import io.advant.orm.examples.dao.BrandDAO;
import io.advant.orm.examples.dao.CategoryDAO;
import io.advant.orm.examples.dao.ProductDAO;
import io.advant.orm.examples.dao.impl.BrandDAOImpl;
import io.advant.orm.examples.dao.impl.CategoryDAOImpl;
import io.advant.orm.examples.dao.impl.ProductDAOImpl;
import io.advant.orm.examples.entity.BrandEntity;
import io.advant.orm.examples.entity.CategoryEntity;
import io.advant.orm.examples.entity.ProductCategoryEntity;
import io.advant.orm.examples.entity.ProductEntity;
import io.advant.orm.examples.exception.ServiceException;
import io.advant.orm.exception.ConnectionException;
import io.advant.orm.exception.OrmException;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Store Service with all application Business Functionality
 *
 * @author Marco Romagnolo
 */
public class StoreService {

    private static final Logger LOGGER = Logger.getLogger(StoreService.class.getName());
    private BrandDAO<BrandEntity> brandDAO;
    private ProductDAO<ProductEntity> productDAO;
    private CategoryDAO<CategoryEntity> categoryDAO;

    public StoreService() throws ServiceException {
        try {
            DBConnection connection = DBFactory.getInstance().getConnection();
            brandDAO = new BrandDAOImpl(connection);
            categoryDAO = new CategoryDAOImpl(connection);
            productDAO = new ProductDAOImpl(connection);
        } catch (ConnectionException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    public List<BrandEntity> getAllBrands() throws ServiceException {
        try {
            return brandDAO.findAll();
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    public List<ProductEntity> getAllProducts() throws ServiceException {
        try {
            return productDAO.findAll();
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    public List<CategoryEntity> getAllCategories() throws ServiceException {
        try {
            return categoryDAO.findAll();
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    public void addProduct(String brandName, String productName, String productDesc, String categoryName) throws ServiceException {
        try {
            // Insert Brand
            BrandEntity brand = new BrandEntity();
            brand.setName(brandName);
            brandDAO.insert(brand);

            // Insert Category
            CategoryEntity category = new CategoryEntity();
            category.setName(categoryName);
            categoryDAO.insert(category);

            // Insert Product
            ProductEntity product = new ProductEntity();
            product.setBrandId(brand.getId());
            product.setName(productName);
            product.setDescription(productDesc);
            product.setCreateDate(new Date());
            productDAO.insert(product);

            // Insert Product-sCategory association
            ProductCategoryEntity prodCategory = new ProductCategoryEntity();
            prodCategory.setCategoryId(category.getId());
            prodCategory.setProductId(product.getId());

        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    public Long findBrandByName(String brandName) throws ServiceException {
        try {
            return brandDAO.findByName(brandName).getId();
        } catch (OrmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e);
        }
    }
}
