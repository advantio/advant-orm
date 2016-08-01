package io.advant.orm.internal;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Created by Marco on 01/08/2016.
 */
public class SqlValue {

    public static void setStatement(PreparedStatement pstmt, int i, Object value) throws SQLException {
        if (value instanceof String) {
            pstmt.setString(i, (String) value);
        } else if (value instanceof Boolean) {
            pstmt.setBoolean(i, (Boolean) value);
        } else if (value instanceof Integer) {
            pstmt.setInt(i, (Integer) value);
        } else if (value instanceof  Long) {
            pstmt.setLong(i, (Long) value);
        } else if (value instanceof  Short) {
            pstmt.setShort(i, (Short) value);
        } else if (value instanceof  Float) {
            pstmt.setFloat(i, (Float) value);
        } else if (value instanceof  Double) {
            pstmt.setDouble(i, (Double) value);
        } else if (value instanceof BigDecimal) {
            pstmt.setBigDecimal(i, (BigDecimal) value);
        } else if (value instanceof Byte) {
            pstmt.setByte(i, (Byte) value);
        } else if (value instanceof  Blob) {
            pstmt.setBlob(i, (Blob) value);
        } else if (value instanceof  Clob) {
            pstmt.setClob(i, (Clob) value);
        } else if (value instanceof Time) {
            pstmt.setTime(i, (Time) value);
        } else if (value instanceof Timestamp) {
            pstmt.setTimestamp(i, (Timestamp) value);
        } else if (value instanceof Date) {
            pstmt.setDate(i, (Date) value);
        } else if (value instanceof Array) {
            pstmt.setArray(i, (Array) value);
        } else if (value instanceof byte[]) {
            pstmt.setBytes(i, (byte[]) value);
        } else if (value instanceof InputStream) {
            pstmt.setBinaryStream(i, (InputStream) value);
        } else if (value instanceof Reader) {
            pstmt.setCharacterStream(i, (Reader) value);
        }
    }

}
