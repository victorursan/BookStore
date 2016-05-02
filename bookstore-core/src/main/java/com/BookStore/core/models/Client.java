package com.BookStore.core.models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity<Integer> implements Serializable {
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="client_book",
            joinColumns = @JoinColumn( name="client_id"),
            inverseJoinColumns = @JoinColumn( name="book_id")
    )
    private Set<Book> books = new HashSet<>();

    public Client() {
        super(1);
    }

    public Client(Integer id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Getter
    public String getFirstName() {
        return firstName;
    }

    @Setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Getter
    public String getLastName() {
        return lastName;
    }

    @Setter
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Getter
    public Set<Book> getBooks() {
        return books;
    }

    @Setter
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void buyBook(Book book) {
        books.add(book);
    }

    public boolean returnBook(Book book) {
        for (Book b : books) {
            if (Objects.equals(b.getId(), book.getId())) {
                return books.remove(b);
            }
        }
        return false;
    }

    public int moneySpent() {
        return this.getBooks().stream().map(Book::getPrice).reduce(0, Integer::sum);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return firstName.equals(client.firstName) && lastName.equals(client.lastName) &&
                (books != null ? books.equals(client.books) : client.books == null);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{ " + getId() + ". First Name: " + firstName + ", Last Name: " + lastName + " Books: [" + books + "] }";
    }
}
