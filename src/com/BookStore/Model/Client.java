package com.BookStore.Model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "client")
public class Client extends BaseEntity<Integer> {
    private String firstName;
    private String lastName;
    private List<Book> books;

    public Client() {
        super(1);
    }

    public Client(Integer id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new ArrayList<>();
    }

    public Client(Integer id, String firstName, String lastName, List<Book> books) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    @Getter
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    @Setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Getter
    public String getLastName() {
        return lastName;
    }

    @XmlElement
    @Setter
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    @Getter
    public List<Book> getBooks() {
        return books;
    }

    @XmlElementWrapper(name = "books")
    @XmlElementRef()
    @Setter
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void buyBook(Book book) {
        books.add(book);
    }

    public boolean returnBook(Book book) {
        return books.remove(book);
    }

    public int moneySpent() {
        return this.getBooks().stream().map(Book::getPrice).reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "{" + getId() + ". First Name: " + firstName + ", Last Name: " + lastName +
                ", Books: " + books.toString() + "}";
    }
}
