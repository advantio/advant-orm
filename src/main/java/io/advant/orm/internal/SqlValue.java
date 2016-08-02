package io.advant.orm.internal;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author Marco Romagnolo
 */
public class SqlValue {

    public static void setStatement(PreparedStatement pstmt, int i, Class<?> type, Object value) throws SQLException {
        if (type == String.class) {
            pstmt.setString(i, (String) value);
        } else if (type == Boolean.class || type == boolean.class) {
            pstmt.setBoolean(i, (Boolean) value);
        } else if (type == Integer.class) {
            pstmt.setInt(i, (Integer) value);
        } else if (type ==  Long.class) {
            pstmt.setLong(i, (Long) value);
        } else if (type == Short.class) {
            pstmt.setShort(i, (Short) value);
        } else if (type == Float.class) {
            pstmt.setFloat(i, (Float) value);
        } else if (type == Double.class) {
            pstmt.setDouble(i, (Double) value);
        } else if (type == BigDecimal.class) {
            pstmt.setBigDecimal(i, (BigDecimal) value);
        } else if (type == Byte.class) {
            pstmt.setByte(i, (Byte) value);
        } else if (type == Blob.class) {
            pstmt.setBlob(i, (Blob) value);
        } else if (type == Clob.class) {
            pstmt.setClob(i, (Clob) value);
        } else if (type == Time.class) {
            pstmt.setTime(i, (Time) value);
        } else if (type == Timestamp.class) {
            pstmt.setTimestamp(i, (Timestamp) value);
        } else if (type == Date.class) {
            pstmt.setDate(i, (Date) value);
        } else if (type == Array.class) {
            pstmt.setArray(i, (Array) value);
        } else if (type == InputStream.class) {
            pstmt.setBinaryStream(i, (InputStream) value);
        } else if (type == Reader.class) {
            pstmt.setCharacterStream(i, (Reader) value);
        } else if (type == Byte[].class) {
            pstmt.setBytes(i, (byte[]) value);
        } else if (type == URL.class) {
            pstmt.setURL(i, (URL) value);
        } else if (type == java.util.Date.class) {
            Date date = (value == null) ? null : new Date(((java.util.Date) value).getTime());
            pstmt.setDate(i, date);
        } else if (type == java.util.Calendar.class) {
            Date date = (value == null) ? null : new Date(((java.util.Calendar) value).getTime().getTime());
            pstmt.setDate(i, date);
        }
    }

}
