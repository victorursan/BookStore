package com.BookStore.Repository;

import com.BookStore.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IRepository<Book> {

    private List<Book> books;

    public BookRepository() {
        books = new ArrayList<>();
    }

    @Override
    public void add(Book b) {
        books.add(b);
    }

    @Override
    public Book get(int id) {
        return books.get(id);
    }

    @Override
    public ArrayList<Book> getAll() {
        return (ArrayList<Book>) books;
    }

    @Override
    public void update(int id, Book b) {
        books.set(id, b);
    }

    @Override
    public void delete(int id) {
        books.remove(id);
    }
}
