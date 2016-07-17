package io.advant.orm.test.exception;

/**
 * @author Marco Romagnolo
 */
public class DataException extends Exception {

    protected DataException(String message) {
        super(message);
    }

    public DataException(Exception e) {
        super(e);
    }
}
