package com.BookStore.web.dto;

import com.BookStore.core.models.Book;

import java.io.Serializable;
import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
public class BooksDto implements Serializable {
    private List<Book> books;

    public BooksDto() {}

    public BooksDto(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BooksDto{ books = " + books + '}';
    }
}
