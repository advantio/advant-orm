-- Create Database and User

CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
CREATE DATABASE IF NOT EXISTS advant_orm;
GRANT ALL PRIVILEGES ON advant_orm . * TO 'test'@'localhost';
FLUSH PRIVILEGES;

-- Create Tables

CREATE TABLE test_brand
(
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  version int(11) DEFAULT NULL,
  name varchar(255) NOT NULL
);


CREATE TABLE test_category
(
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  version int(11) DEFAULT NULL,
  name varchar(255) NOT NULL,
  description varchar(1000)
);


CREATE TABLE test_product_category
(
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  version int(11) DEFAULT NULL,
  product_id int(11) NOT NULL,
  category_id int(11) NOT NULL
);


CREATE TABLE test_product
(
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  version int(11) DEFAULT NULL,
  brand_id int(11) NOT NULL,
  blocked BOOLEAN NOT NULL,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  create_date DATE NOT NULL,
  modify_date DATE
);



