-- Create Database

CREATE USER test WITH PASSWORD 'test' CREATEDB;
	   
CREATE DATABASE advant_orm
  WITH OWNER = test
       TEMPLATE = template0
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;

--Create Sequences

CREATE SEQUENCE brand_id_seq;
CREATE SEQUENCE product_id_seq;
CREATE SEQUENCE category_id_seq;
CREATE SEQUENCE product_category_id_seq;

--Create Tables

CREATE TABLE test_brand
(
  id integer NOT NULL DEFAULT nextval('brand_id_seq'::regclass),
  version integer DEFAULT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT pk_brand PRIMARY KEY (id)
);


CREATE TABLE test_category
(
  id integer NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  version integer DEFAULT NULL,
  name character varying(255) NOT NULL,
  description character varying(1000) NOT NULL,
  CONSTRAINT pk_category PRIMARY KEY (id)
);


CREATE TABLE test_product_category
(
  id integer NOT NULL DEFAULT nextval('product_category_id_seq'::regclass),
  version integer DEFAULT NULL,
  product_id integer NOT NULL,
  category_id integer NOT NULL,
  CONSTRAINT pk_product_category PRIMARY KEY (id)
);


CREATE TABLE test_product
(
  id integer NOT NULL DEFAULT nextval('product_id_seq'::regclass),
  version integer DEFAULT NULL,
  brand_id integer NOT NULL,
  blocked boolean NOT NULL DEFAULT false,
  name character varying(255) NOT NULL,
  description character varying(255) NOT NULL,
  create_date timestamp without time zone NOT NULL,
  modify_date timestamp without time zone,
  CONSTRAINT pk_product PRIMARY KEY (id)
);
