package com.BookStore.service;

import com.BookStore.core.models.Book;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
public interface BookService {
    List<Book> findAll();
}
