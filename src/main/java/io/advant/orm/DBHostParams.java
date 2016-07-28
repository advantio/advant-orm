package io.advant.orm;

import io.advant.orm.internal.AbstractParams;
import io.advant.orm.type.DBHostType;

import java.util.Properties;
import java.util.Set;

/**
 *
 */
public class DBHostParams extends AbstractParams {

    private DBHostType dbType;
    private String host;
    private int port;
    private String database;

    public DBHostParams(DBHostType dbType, String host, int port, String database, String user, String password, Set<String> entities) {
        super(user, password, entities);
        this.dbType = dbType;
        this.host = host;
        this.port = port;
        this.database = database;
        configure();
    }

    public DBHostParams(DBHostType dbType, String host, int port, String database, String user, String password, Set<String> entities, Properties properties) {
        super(user, password, entities, properties);
        this.dbType = dbType;
        this.host = host;
        this.port = port;
        this.database = database;
        configure();
    }

    private void configure() {
        switch (dbType) {
            case MYSQL:
                setUri("jdbc:mysql://" + host + ":" + port + "/" + database);
                break;
            case POSTGRESQL:
                setUri("jdbc:postgresql://" + host + ":" + port + "/" + database);
                break;
            case IBMDB2:
                setUri("jdbc:db2://" + host + ":" + port + "/" + database);
                break;
            case MSSQL:
                setUri("jdbc:microsoft:sqlserver://" + host + ":" + port + "/" + database);
                break;
            case SYBASE:
                setUri("jdbc:jtds:sybase://" + host + ":" + port + "/" + database);
                break;
            case ORACLE:
                setUri("jdbc:oracle:thin:@" + host + ":" + port + "/" + database);
                break;
        }
    }

    public DBHostType getDbType() {
        return dbType;
    }

    public void setDbType(DBHostType dbType) {
        this.dbType = dbType;
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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
