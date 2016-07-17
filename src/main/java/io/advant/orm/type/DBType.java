package io.advant.orm.type;

/**
 * Created by Marco on 02/07/2016.
 */
public enum DBType {
    MYSQL("com.mysql.jdbc.Driver"),
    POSTGRESQL("org.postgresql.Driver"),
    IBMDB2("COM.ibm.db2.jdbc.app.DB2Driver"),
    MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDrive"),
    ORACLE("oracle.jdbc.driver.OracleDriver");

    private final String driver;

    DBType(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
