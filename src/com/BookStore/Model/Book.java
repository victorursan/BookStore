package com.BookStore.Model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "book")
public class Book extends BaseEntity<Integer> {
    private String title;
    private String author;
    private Long ISBN;
    private String genre;
    private String publisher;
    private Integer price;
    private Boolean available;


    public Book() {
        super(1);
    }

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

    @Getter
    public Long getISBN() {
        return ISBN;
    }

    @XmlElement
    @Setter
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    @Getter
    public String getGenre() {
        return genre;
    }

    @XmlElement
    @Setter
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Getter
    public String getPublisher() {
        return publisher;
    }

    @XmlElement
    @Setter
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Getter
    public Integer getPrice() {
        return price;
    }

    @XmlElement
    @Setter
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Getter
    public String getAuthor() {
        return author;
    }

    @XmlElement
    @Setter
    public void setAuthor(String author) {
        this.author = author;
    }

    @Getter
    public String getTitle() {
        return title;
    }

    @XmlElement
    @Setter
    public void setTitle(String title) {
        this.title = title;
    }

    @Getter
    public Boolean isAvailable() {
        return available;
    }

    @XmlElement
    @Setter
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return getId() + ", " + title + ", " + author + ", " + ISBN + ", " + genre + ", " + publisher + ", " + price + ", " + available;
    }
}