-- Create Tables

CREATE TABLE test_brand
(
  id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  version INT DEFAULT NULL,
  name VARCHAR(255) NOT NULL
);


CREATE TABLE test_category
(
  id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  version INT DEFAULT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000)
);


CREATE TABLE test_product_category
(
  id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  version INT DEFAULT NULL,
  product_id INT NOT NULL,
  category_id INT NOT NULL
);


CREATE TABLE test_product
(
  id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  version INT DEFAULT NULL,
  brand_id INT NOT NULL,
  blocked BOOLEAN NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  create_date DATE NOT NULL,
  modify_date DATE DEFAULT NULL
);

