package com.BookStore.core.service;

import com.BookStore.core.models.Book;
import com.BookStore.core.models.Client;
import com.BookStore.core.repositories.BookRepository;
import com.BookStore.core.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by victor on 4/28/16.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @Override
    @Transactional
    public Client updateClient(Integer clientId, String firstName, String lastName, Set<Integer> books) {
        Client client = clientRepository.findOne(clientId);
        client.setFirstName(firstName);
        client.setLastName(lastName);

        Set<Integer> clientBooks = client.getBooks().stream().map(Book::getId).collect(Collectors.toSet());

        Set<Integer> toRemove = clientBooks.stream().filter(book -> !books.contains(book)).collect(Collectors.toSet());
        Set<Integer> toAdd = books.stream().filter(book -> !clientBooks.contains(book)).collect(Collectors.toSet());
                clientBooks.stream().filter(books::contains).collect(Collectors.toSet());

        List<Book> bookListAdd = bookRepository.findAll(toAdd);
        bookListAdd.stream().forEach(client::buyBook);

        List<Book> bookListReturn = bookRepository.findAll(toRemove);
        bookListReturn.stream().forEach(client::returnBook);

        return client;
    }

    @Override
    public Client createClient(String firstName, String lastName) {
        return clientRepository.save(new Client(firstName, lastName));
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientRepository.delete(clientId);
    }


}
