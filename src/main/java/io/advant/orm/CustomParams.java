package io.advant.orm;

import io.advant.orm.internal.AbstractParams;

import java.util.Properties;
import java.util.Set;

/**
 * Created by Marco on 28/07/2016.
 */
public abstract class CustomParams extends AbstractParams implements Params {

    private String driver;
    private String uri;

    public CustomParams(String driver, String uri, String user, String password) {
        super(user, password);
        setDriver(driver);
        setUri(uri);
    }

    public CustomParams(String driver, String uri, String user, String password, Properties properties) {
        super(user, password, properties);
        setDriver(driver);
        setUri(uri);
    }

}
