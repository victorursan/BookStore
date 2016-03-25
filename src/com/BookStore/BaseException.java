package com.BookStore;

public class BaseException extends RuntimeException {
    private Throwable parentException;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable e) {
        super(e.getMessage());
        this.parentException = e;
    }

    public Throwable getParentException() {
        return parentException;
    }
}
