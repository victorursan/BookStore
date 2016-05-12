package com.BookStore.core.service;

import com.BookStore.core.models.Book;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
public interface BookService {
    List<Book> findAll();

    Book updateBook(Integer bookId, String title, String author, Long ISBN, String genre, String publisher, Integer price, Boolean available);

    Book createBook(String title, String author, Long ISBN, String genre, String publisher, Integer price, Boolean available);

    void deleteBook(Integer bookId);
}
