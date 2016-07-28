package io.advant.orm;

import io.advant.orm.internal.AbstractParams;
import io.advant.orm.type.DBLocalType;

import java.util.Properties;
import java.util.Set;

/**
 *
 */
public class DBLocalParams extends AbstractParams {

    private String database;
    private DBLocalType dbType;

    public DBLocalParams(DBLocalType dbType, String database, String user, String password, Set<String> entities) {
        super(user, password, entities);
        this.dbType = dbType;
        this.database = database;
        configure();
    }

    public DBLocalParams(DBLocalType dbType, String database, String user, String password, Set<String> entities, Properties properties) {
        super(user, password, entities, properties);
        this.dbType = dbType;
        this.database = database;
        configure();
    }

    private void configure() {
        switch (dbType) {
            case DERBY:
                setUri("jdbc:derby:" + database);
                break;
            case H2:
                setUri("jdbc:h2:" + database);
                break;
            case HSQLDB:
                setUri("jdbc:hsqldb:" + database);
                break;
        }
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
