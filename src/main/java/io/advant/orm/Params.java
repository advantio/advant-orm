/**
 * Copyright 2016 Advant I/O
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.advant.orm;

import io.advant.orm.type.DBType;

import java.util.Properties;
import java.util.Set;

/**
 * 
 */
public class Params {

    private DBType dbType;
    private String driver;
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

    public Params(String driver, String host, int port, String database, String user, String password, Set<String> entities) {
        this.driver = driver;
        this.database = database;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.entities = entities;
    }

    public Params(String driver, String host, int port, String database, String user, String password, Set<String> entities, Properties properties) {
        this(driver, host, port, database, user, password, entities);
        this.properties = properties;
    }

    public Params(DBType dbType, String host, int port, String database, String user, String password, Set<String> entities, Properties properties) {
        this(dbType, host, port, database, user, password, entities);
        this.properties = properties;
    }

    public DBType getDbType() {
        return dbType;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
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
