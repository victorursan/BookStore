package com.BookStore.core.service;

import com.BookStore.core.models.Book;
import com.BookStore.core.models.Client;

import java.util.List;

/**
 * Created by victor on 5/13/16.
 */
public interface BookStoreService {
    List<Book> booksMoreExpensiveThan(Integer value);
    Client clientThatSpentMost();
}
