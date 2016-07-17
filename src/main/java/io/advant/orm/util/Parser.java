package io.advant.orm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by francesca on 03/07/16.
 */
public class Parser {

    public static Object parse(Object value) {
        if (value instanceof Date) {
            return date((Date) value);
        } else if (value instanceof Boolean) {
            return (boolean)value ? 1 : 0;
        }
        return value;
    }

    public static String date(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }
}
