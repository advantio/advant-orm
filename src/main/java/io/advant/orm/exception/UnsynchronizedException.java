package io.advant.orm.exception;

/**
 * @author Marco Romagnolo
 */
public class UnsynchronizedException extends Exception {
    public UnsynchronizedException() {
        super("Entity Id or Version are not Synchronized with database");
    }
}
