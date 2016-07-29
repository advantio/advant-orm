package io.advant.orm.internal;

import io.advant.orm.Params;

import java.util.Properties;
import java.util.Set;

/**
 * Created by Marco on 28/07/2016.
 */
public class AbstractParams implements Params {

    private String driver;
    private String uri;
    private String user;
    private String password;
    private Properties properties;

    public AbstractParams( String user, String password) {
        this.password = password;
        this.user = user;
        configure();
    }

    public AbstractParams(String user, String password, Properties properties) {
        this.user = user;
        this.password = password;
        this.properties = properties;
        configure();
    }

    private void configure() {
        if (properties == null) {
            properties = new Properties ();
        }
        if (user != null) {
            properties.put("user", user);
        }
        if (password != null) {
            properties.put("password", password);
        }
    }

    @Override
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    @Override
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
