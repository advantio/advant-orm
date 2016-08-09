package io.advant.orm;

import io.advant.orm.internal.AbstractParams;
import io.advant.orm.type.DBType;

import java.util.Properties;

/**
 *
 */
public class DBLocalParams extends AbstractParams {

    public DBLocalParams(DBType dbType, String database, String user, String password) {
        this(dbType, database, user, password, null);
    }

    public DBLocalParams(DBType dbType, String database, String user, String password, Properties properties) {
        super(dbType, database, user, password, properties);
        setUri();
    }

    private void setUri() {
        switch (getDBType()) {
            case DERBY:
                setUri("jdbc:derby:" + getDatabase());
                break;
            case H2:
                setUri("jdbc:h2:" + getDatabase());
                break;
            case HSQLDB:
                setUri("jdbc:hsqldb:" + getDatabase());
                break;
            case SQLITE:
                setUri("jdbc:sqlite:" + getDatabase());
        }
    }

}
