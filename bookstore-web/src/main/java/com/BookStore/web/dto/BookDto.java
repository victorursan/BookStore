package com.BookStore.web.dto;

import com.BookStore.core.models.Book;

/**
 * Created by victor on 5/13/16.
 */
public class BookDto {
    private Book book;

    public BookDto() {}

    public BookDto(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getISBN() {
        return book.getISBN();
    }

    public void setISBN(Long ISBN) {
        book.setISBN(ISBN);
    }

    public String getGenre() {
        return book.getGenre();
    }

    public void setGenre(String genre) {
        book.setGenre(genre);
    }

    public String getPublisher() {
        return book.getPublisher();
    }

    public void setPublisher(String publisher) {
        book.setPublisher(publisher);
    }

    public Integer getPrice() {
        return book.getPrice();
    }

    public void setPrice(Integer price) {
        book.setPrice(price);
    }

    public String getAuthor() {
        return book.getAuthor();
    }

    public void setAuthor(String author) {
        book.setAuthor(author);
    }

    public String getTitle() {
        return book.getTitle();
    }

    public void setTitle(String title) {
        book.setTitle(title);
    }

    public Boolean isAvailable() {
        return book.isAvailable();
    }

    public void setAvailable(Boolean available) {
        book.setAvailable(available);
    }


}
