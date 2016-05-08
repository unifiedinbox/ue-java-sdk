package com.unificationengine.exceptions;

/**
 * Created by deadlock on 4/5/16.
 */
public class UnificationEngineException extends Exception {

    public UnificationEngineException() {
        super();
    }

    public UnificationEngineException(String message) {
        super(message);
    }

    public UnificationEngineException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnificationEngineException(Throwable cause) {
        super(cause);
    }

    public UnificationEngineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public String getMessage() {
        System.out.println("From Exception Class");
        System.out.println(this.getMessage());
    }
}
