package com.BookStore.service;


import com.BookStore.ControllerService;
import com.BookStore.Models.Book;

import java.util.Arrays;
import java.util.List;

public class ControllerServiceServer implements ControllerService {

    @Override
    public List<Book> getAllBooks() {
            return Arrays.asList(new Book(10,"a", "b", 2L, "c", "d", 11, true), new Book(11,"a1", "b1", 3L, "c1", "d1", 112, true));
    }

}