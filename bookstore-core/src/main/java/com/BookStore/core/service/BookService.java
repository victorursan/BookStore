package com.BookStore.core.service;

import com.BookStore.core.models.Book;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
public interface BookService {
    List<Book> findAll();

    Book updateBook(Integer bookId, String title, Long ISBN, Integer year, Integer authorId);

    Book createBook(String title, Long ISBN, Integer year, Integer authorId);

    void deleteBook(Integer bookId);
}
