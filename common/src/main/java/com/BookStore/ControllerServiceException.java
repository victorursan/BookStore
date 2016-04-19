package com.BookStore;

/**
 * Created by victor on 4/13/16.
 */
public class ControllerServiceException extends RuntimeException {

    public ControllerServiceException(Throwable cause) {
        super(cause);
    }

    public ControllerServiceException(String message) {
        super(message);
    }

}
