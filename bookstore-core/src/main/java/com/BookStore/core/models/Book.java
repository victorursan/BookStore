package com.BookStore.core.models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="book")
public class Book extends BaseEntity<Integer> implements Serializable {
    @Column(name="title", nullable = false)
    private String title;

    @Column(name="author", nullable = false)
    private String author;

    @Column(name="isbn", nullable = false)
    private Long isbn;

    @Column(name="genre", nullable = false)
    private String genre;

    @Column(name="publisher", nullable = false)
    private String publisher;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(name="available", nullable = false)
    private Boolean available;

//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<ClientBook> clientBooks = new HashSet<>();

    public Book() {
    }

    public Book(String title, String author, Long isbn, String genre, String publisher,
                Integer price, Boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.publisher = publisher;
        this.price = price;
        this.available = available;
    }

    public Book(String title, String author, Long isbn, String genre, String publisher, Integer price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.publisher = publisher;
        this.price = price;
        this.available = true;
    }

    @Getter
    public Long getIsbn() {
        return isbn;
    }

    @Setter
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
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

        return title.equals(book.title) && author.equals(book.author) && isbn.equals(book.isbn) &&
                genre.equals(book.genre) && publisher.equals(book.publisher) && price.equals(book.price) &&
                available.equals(book.available);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + available.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{ " + getId() + ", " + title + ", " + author + ", " + isbn + ", " + genre + ", " + publisher +
                ", " + price + ", " + available + " }";
    }
}