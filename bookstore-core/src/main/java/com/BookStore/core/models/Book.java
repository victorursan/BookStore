package com.BookStore.core.models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="books")
public class Book extends BaseEntity<Integer> implements Serializable {
    @Column(name="title", nullable = false)
    private String title;

    @Column(name="author", nullable = false)
    private String author;

    @Column(name="ISBN", nullable = false)
    private Long ISBN;

    @Column(name="genre", nullable = false)
    private String genre;

    @Column(name="publisher", nullable = false)
    private String publisher;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(name="available", nullable = false)
    private Boolean available;

    public Book(Integer id, String title, String author, Long ISBN, String genre, String publisher,
                Integer price, Boolean available) {
        super(id);
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publisher = publisher;
        this.price = price;
        this.available = available;
    }

    public Book(Integer id, String title, String author, Long ISBN, String genre, String publisher, Integer price) {
        super(id);
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publisher = publisher;
        this.price = price;
        this.available = true;
    }

    public Book() {
        super(1);
    }

    @Getter
    public Long getISBN() {
        return ISBN;
    }

    @Setter
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    @Getter
    public String getGenre() {
        return genre;
    }

    @Setter
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Getter
    public String getPublisher() {
        return publisher;
    }

    @Setter
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Getter
    public Integer getPrice() {
        return price;
    }

    @Setter
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Getter
    public String getAuthor() {
        return author;
    }

    @Setter
    public void setAuthor(String author) {
        this.author = author;
    }

    @Getter
    public String getTitle() {
        return title;
    }

    @Setter
    public void setTitle(String title) {
        this.title = title;
    }

    @Getter
    public Boolean isAvailable() {
        return available;
    }

    @Setter
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return title.equals(book.title) && author.equals(book.author) && ISBN.equals(book.ISBN) &&
                genre.equals(book.genre) && publisher.equals(book.publisher) && price.equals(book.price) &&
                available.equals(book.available);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + ISBN.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + available.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{ " + getId() + ", " + title + ", " + author + ", " + ISBN + ", " + genre + ", " + publisher +
                ", " + price + ", " + available + " }";
    }
}