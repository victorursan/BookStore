package com.BookStore.Model;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Long ISBN;
    private String genre;
    private String publisher;
    private Integer price;

    public Book(Integer id, String title, String author, Long ISBN, String genre, String publisher, Integer price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publisher = publisher;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
