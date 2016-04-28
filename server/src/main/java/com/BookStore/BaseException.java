package com.BookStore;

public class BaseException extends RuntimeException {
    private Exception parentException;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Exception e) {
        super(e.getMessage());
        this.parentException = e;
    }

    public Exception getParentException() {
        return parentException;
    }
}
