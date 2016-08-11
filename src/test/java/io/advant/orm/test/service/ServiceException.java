package io.advant.orm.test.service;

/**
 * @author Marco Romagnolo
 */
public class ServiceException extends Exception {
    public ServiceException(Exception e) {
        super(e);
    }
}
