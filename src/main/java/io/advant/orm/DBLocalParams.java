package io.advant.orm;

import io.advant.orm.internal.AbstractParams;
import io.advant.orm.type.DBLocalType;

import java.util.Properties;

/**
 *
 */
public class DBLocalParams extends AbstractParams {

    private String database;
    private DBLocalType dbType;

    public DBLocalParams(DBLocalType dbType, String database, String user, String password) {
        super(user, password);
        this.dbType = dbType;
        this.database = database;
        setDriver(dbType.getDriver());
        setUri();
    }

    public DBLocalParams(DBLocalType dbType, String database, String user, String password, Properties properties) {
        super(user, password, properties);
        this.dbType = dbType;
        this.database = database;
        setDriver(dbType.getDriver());
        setUri();
    }

    private void setUri() {
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
            case SQLITE:
                setUri("jdbc:sqlite:" + database);
        }
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
