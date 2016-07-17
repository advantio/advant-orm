package io.advant.orm;

import io.advant.orm.type.DBType;

import java.util.Properties;
import java.util.Set;

/**
 * @author Marco Romagnolo
 */
public class Params {

    private DBType dbType;
    private String database;
    private String host;
    private int port;
    private String user;
    private String password;
    private Set<String> entities;
    private Properties properties;

    public Params(DBType dbType, String host, int port, String database, String user, String password, Set<String> entities) {
        this.dbType = dbType;
        this.database = database;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.entities = entities;
    }

    public Params(DBType dbType, String host, int port, String database, String user, String password, Set<String> entities, Properties properties) {
        this.dbType = dbType;
        this.database = database;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.entities = entities;
        this.properties = properties;
    }

    public DBType getDbType() {
        return dbType;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getEntities() {
        return entities;
    }

    public void setEntities(Set<String> entities) {
        this.entities = entities;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Params{" +
                "dbType=" + dbType +
                ", database='" + database + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", entities=" + entities +
                ", properties=" + properties +
                '}';
    }
}
