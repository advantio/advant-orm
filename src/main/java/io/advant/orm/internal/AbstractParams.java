package io.advant.orm.internal;

import io.advant.orm.Params;
import io.advant.orm.type.DBType;

import java.util.Properties;
import java.util.Set;

/**
 * @author Marco Romagnolo
 */
public class AbstractParams implements Params {

    private final DBType dbType;
    private final String database;
    private final String user;
    private final String password;
    private final Properties properties;
    private final String driver;
    private String uri;

    public AbstractParams(DBType dbType, String database, String user, String password) {
        this(dbType, database, user, password, null);
    }

    public AbstractParams(DBType dbType, String database, String user, String password, Properties properties) {
        this.dbType = dbType;
        this.driver = dbType.getDriver();
        this.database = database;
        this.user = user;
        this.password = password;
        if (properties == null) {
            properties = new Properties ();
        }
        if (user != null) {
            properties.put("user", user);
        }
        if (password != null) {
            properties.put("password", password);
        }
        this.properties = properties;
    }

    @Override
    public DBType getDBType() {
        return dbType;
    }

    @Override
    public String getDatabase() {
        return database;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
