package io.advant.orm.exception;

/**
 * Created by Marco on 02/07/2016.
 */
public class ConnectionException extends Exception {

    public ConnectionException(Exception e) {
        super(e);
    }
}
