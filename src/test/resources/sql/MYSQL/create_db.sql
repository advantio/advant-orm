CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
CREATE DATABASE IF NOT EXISTS advant_orm;
GRANT ALL PRIVILEGES ON advant_orm . * TO 'test'@'localhost';
FLUSH PRIVILEGES;