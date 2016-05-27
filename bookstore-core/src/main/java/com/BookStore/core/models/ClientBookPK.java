package com.BookStore.core.models;

import java.io.Serializable;

/**
 * Created by victor on 5/13/16.
 */
public class ClientBookPK implements Serializable {
    private Book book;
    private Client client;

    public ClientBookPK() {
    }

    public ClientBookPK(Book book, Client client) {
        this.book = book;
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientBookPK that = (ClientBookPK) o;

        return book.equals(that.book) && client.equals(that.client);
    }

    @Override
    public int hashCode() {
        int result = book.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }
}
