package com.BookStore.core.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by victor on 5/13/16.
 */

@Entity
@Table(name="client_book")
@IdClass(ClientBookPK.class)
public class ClientBook implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientBook that = (ClientBook) o;

        return client.equals(that.client) && book.equals(that.book);

    }

    @Override
    public int hashCode() {
        int result = client.hashCode();
        result = 31 * result + book.hashCode();
        return result;
    }
}

