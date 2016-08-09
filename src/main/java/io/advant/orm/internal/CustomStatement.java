package io.advant.orm.internal;

import io.advant.orm.DBConnection;
import io.advant.orm.type.DBType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Marco Romagnolo
 */
public class CustomStatement {

    private final DBConnection connection;
    private DBType dbType;

    public CustomStatement(DBConnection connection) {
        this.connection = connection;
        this.dbType = connection.getDbType();
    }

    public PreparedStatement forInsert(String sql) throws SQLException {
        String[] ids;
        if (dbType == DBType.POSTGRESQL) {
            ids = new String[]{"id"};
        } else {
            ids = new String[]{"ID"};
        }
        return connection.prepareStatement(sql, ids);
    }


}
