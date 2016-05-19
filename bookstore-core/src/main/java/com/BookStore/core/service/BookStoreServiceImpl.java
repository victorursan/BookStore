package com.BookStore.core.service;

import com.BookStore.core.models.Book;
import com.BookStore.core.models.Client;
import com.BookStore.core.repositories.BookRepository;
import com.BookStore.core.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by victor on 5/13/16.
 */
@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void buyBook(Client client, Book book) {

    }

    @Override
    public void returnBook(Client client, Book book) {

    }

    @Override
    public List<Book> getAvailableBooks() {
        return null;
    }

    @Override
    public List<Client> getClients() {
        return null;
    }
}
