package com.BookStore.core.service;

import com.BookStore.core.models.Book;
import com.BookStore.core.models.Client;
import com.BookStore.core.repositories.BookRepository;
import com.BookStore.core.repositories.ClientRepository;
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

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Book> booksMoreExpensiveThan(Integer value) {
        return bookRepository.findAll().stream().filter(book -> book.getPrice() < value).collect(Collectors.toList());
    }

    @Override
    public Client clientThatSpentMost() {
        Optional<Client> client = clientRepository.findAll().stream().max(Comparator.comparingInt(Client::moneySpent));
        if (client.isPresent())
            return client.get();
        return null;
    }
}
