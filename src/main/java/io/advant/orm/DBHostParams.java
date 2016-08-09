package io.advant.orm;

import io.advant.orm.internal.AbstractParams;
import io.advant.orm.type.DBType;

import java.util.Properties;

/**
 * @author Marco Romagnolo
 */
public class DBHostParams extends AbstractParams {

    private final String host;
    private final int port;

    public DBHostParams(DBType dbType, String host, int port, String database, String user, String password) {
        this(dbType, host, port, database, user, password, null);
    }

    public DBHostParams(DBType dbType, String host, int port, String database, String user, String password, Properties properties) {
        super(dbType, database, user, password, properties);
        this.host = host;
        this.port = port;
        setUri();
    }

    private void setUri() {
        switch (getDBType()) {
            case MYSQL:
                setUri("jdbc:mysql://" + host + ":" + port + "/" + getDatabase());
                break;
            case POSTGRESQL:
                setUri("jdbc:postgresql://" + host + ":" + port + "/" + getDatabase());
                break;
            case IBMDB2:
                setUri("jdbc:db2://" + host + ":" + port + "/" + getDatabase());
                break;
            case MSSQL:
                setUri("jdbc:microsoft:sqlserver://" + host + ":" + port + "/" + getDatabase());
                break;
            case SYBASE:
                setUri("jdbc:jtds:sybase://" + host + ":" + port + "/" + getDatabase());
                break;
            case ORACLE:
                setUri("jdbc:oracle:thin:@" + host + ":" + port + ":" + getDatabase());
                break;
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
