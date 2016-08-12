package io.advant.orm.internal;

import java.util.Properties;

/**
 * @author Marco Romagnolo
 */
public interface DBConnectionParams {

    String getDataSource();

    String getUri();

    Properties getProperties();

}
