# Advant ORM

[![Build Status](https://travis-ci.org/advantio/advant-orm.svg?branch=master)](https://travis-ci.org/advantio/advant-orm)  
**Maven Repository:** [advant-orm-1.0.jar](http://search.maven.org/#artifactdetails%7Cio.advant%7Cadvant-orm%7C1.0%7Cjar)  
**Wiki:** [User Guide 1.0](https://github.com/advantio/advant-orm/wiki/Advant-ORM-1.0)  
**Documentation:** [Javadocs 1.0](http://advantio.github.io/advant-orm/api-docs/1.0/javadoc/index.html)  
**License:** [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)  

## Getting Started

This is the simplest ORM (Object Relational Mapping) to work with all popular databases (RDBMS) 
- Mysql
- Postgres
- SQLite
- H2
- HSQLDB
- Derby
- Oracle
- DB2

### Prerequisites
You must comply with these requirements before implement Advant ORM in your application: 
- JVM >= 7
- JDBC dependencies correctly configured in your project
- Database correctly installed and accessible with your credentials

### Example with Mysql
Create a **db** schema if you haven't yet
~~~~
CREATE DATABASE advantorm;
~~~~
Create **table** with ID (AUTO_INCREMENT) and VERSION columns
~~~~
CREATE TABLE person
(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  version BIGINT DEFAULT NULL,
  name varchar(255) NOT NULL,
);
~~~~
Create **Table class** extending `io.advant.orm.AbstractTable` with `@Table` and `@Column` annotations
~~~~
@Table(name = "person")
public class PersonTable extends AbstractTable {
    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
~~~~
Create **Entity** class extending PersonTable and implementing `io.advant.orm.Entity`
~~~~
public class PersonEntity extends Personable implements Entity {}
~~~~
Create **DAO interface** extending `io.advant.orm.DAO` with generics
~~~~
public interface PersonDAO<T> extends DAO<T> {}
~~~~
Create **DAO implementation** class extending `io.advant.orm.AbstractDAO` and generics configured with entity class
~~~~
public class PersonDAOImpl extends AbstractDAO<PersonEntity> implements PersonDAO<PersonEntity> {}
~~~~
Configure **advantorm.properties** file with entities, database credentials and parameters
~~~~
db.type=mysql
db.host=localhost
db.port=3306
db.database=advantorm
db.user=root
db.password=
entity.1=com.example.entity.PersonEntity
~~~~
Now you can **select() insert() update() and delete()** from Person table
~~~~
public static void main() throws ConnectionException, OrmException {
    // Load DB instance from advantorm.properties
    DB db = DBFactory.getInstance();
    
    // Initialize DAO with DBConnection
    PersonDAO<PersonEntity> dao = new PersonDAOImpl(db.getConnection());
    
    //Create your entity and set values
    PersonEntity entity = new PersonEntity();
    entity.setName("Marco");
    
    // test DAO functionality
    dao.insert(entity);
    dao.find(1L);
    entity.setName("Marco2");
    dao.update(entity);
    dao.delete(entity);
    
    // Disconnect
    DB.getInstance().disconnect();
}
~~~~
You can also use `io.advant.orm.GenericDAO` by using entity on fly without create DAOs for each entity

### Insight
if you want to increase your Advant ORM knowledge follow this [link](https://github.com/advantio/advant-orm/wiki)
