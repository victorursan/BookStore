package com.BookStore.Model;

public class Purchase {
    private Integer clientId;
    private Integer bookId;

    public Purchase(Integer clientId, Integer bookId) {
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getBookId() {
        return bookId;
    }
}
