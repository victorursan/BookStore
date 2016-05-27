package com.BookStore.core.service;

import com.BookStore.core.models.Client;

import java.util.List;
import java.util.Set;

/**
 * Created by victor on 4/28/16.
 */
public interface ClientService {
    List<Client> findAll();

    Client updateClient(Integer clientId, String firstName, String lastName, Set<Integer> books);

    Client createClient(String firstName, String lastName);

    void deleteClient(Integer clientId);
}
