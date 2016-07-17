package io.advant.orm.exception;

/**
 * Created by francesca on 04/07/16.
 */
public class TableParseException extends Exception {

    public TableParseException(Exception e) {
        super(e);
    }

    public TableParseException(String s) {
        super(s);
    }
}
