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
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client updateClient(Integer clientId, String firstName, String lastName, Set<Integer> books) {

        Client client = clientRepository.findOne(clientId);
        client.setFirstName(firstName);
        client.setLastName(lastName);

        client.getBooks().stream().map(Book::getId).forEach(books::remove);
        List<Book> disciplineList = bookRepository.findAll(books);
        disciplineList.stream().forEach(client::buyBook);

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
