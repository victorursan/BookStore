package com.BookStore.core.models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "client")
public class Client extends BaseEntity<Integer> implements Serializable {
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ClientBook> clientBooks = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName) {
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
        return Collections.unmodifiableSet(
                        clientBooks.stream()
                        .map(ClientBook::getBook)
                        .collect(Collectors.toSet()));
    }

    public void buyBook(Book book) {
        ClientBook clientBook = new ClientBook();
        clientBook.setBook(book);
        clientBook.setClient(this);
        clientBooks.add(clientBook);
    }

    public boolean returnBook(Book book) {
        clientBooks.stream().filter(clientBook -> clientBook.getBook().equals(book)).forEach(clientBooks::remove);
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

        return firstName.equals(client.firstName) && lastName.equals(client.lastName);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
