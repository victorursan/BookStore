package com.BookStore.core.service;

import com.BookStore.core.models.Client;
import com.BookStore.core.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client updateClient(Integer clientId, String firstName, String lastName) {

        Client client = clientRepository.findOne(clientId);
        client.setFirstName(firstName);
        client.setLastName(lastName);

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
