package io.advant.orm.type;

/**
 * Created by Marco on 28/07/2016.
 */
public enum DBLocalType {

    DERBY("org.apache.derby.jdbc.EmbeddedDriver"),
    H2("org.h2.Driver"),
    HSQLDB("org.hsqldb.jdbc.JDBCDriver"),
    SQLITE("org.sqlite.JDBC");

    private final String driver;

    DBLocalType(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
