CREATE USER test WITH PASSWORD 'test' CREATEDB;
	   
CREATE DATABASE advant_orm
  WITH OWNER = test
       TEMPLATE = template0
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;