package com.BookStore.core.service;

import com.BookStore.core.models.Book;
import com.BookStore.core.models.Client;
import com.BookStore.core.repositories.BookRepository;
import com.BookStore.core.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by victor on 5/13/16.
 */
@Service
public class BookStoreServiceImpl implements BookStoreService {

    private static final Logger log = LoggerFactory.getLogger(BookStoreServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Book> booksMoreExpensiveThan(Integer value) {
        log.trace("booksMoreExpensiveThan: value = {}", value);
        List<Book> bookList = bookRepository.findAll().stream().filter(book -> book.getPrice() < value).collect(Collectors.toList());
        log.trace("booksMoreExpensiveThan: books = {}", bookList);
        return bookList;
    }

    @Override
    public Client clientThatSpentMost() {
        log.trace("clientThatSpentMost");
        Optional<Client> client = clientRepository.findAll().stream().max(Comparator.comparingInt(Client::moneySpent));
        if (client.isPresent()) {
            log.trace("clientThatSpentMost: client = {}", client.get());
            return client.get();
        }
        log.trace("clientThatSpentMost: client = {}", client);
        return null;
    }
}
