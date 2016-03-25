package com.BookStore.Repository.Exceptions;

import com.BookStore.BaseException;

public class RepositoryException extends BaseException {
    public RepositoryException(Throwable exception) {
        super(exception);
    }

    public RepositoryException(String message) {
        super(message);
    }
}