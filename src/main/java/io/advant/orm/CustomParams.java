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

    public CustomParams(String driver, String uri, String user, String password, Set<String> entities) {
        super(user, password, entities);
        setDriver(driver);
        setUri(uri);
    }

    public CustomParams(String driver, String uri, String user, String password, Set<String> entities, Properties properties) {
        super(user, password, entities, properties);
        setDriver(driver);
        setUri(uri);
    }

}
