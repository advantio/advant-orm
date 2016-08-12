package io.advant.orm;

import io.advant.orm.internal.DBImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class DBMultiFactory {

    private static final Logger LOGGER = Logger.getLogger(DBFactory.class.getName());
    private static Map<String, DB> instances = new HashMap<>();

    private DBMultiFactory() {}

    public void add(String key, DBConfig config) {
        instances.put(key, new DBImpl(config));
    }

    public void remove(String key) {
        if (instances.containsKey(key)) {
            instances.get(key).disconnect();
            instances.remove(key);
        }
    }

    public static Map<String, DB> getInstances() {
        return instances;
    }

    public static DB getInstance(String key) {
        return instances.get(key);
    }
}
