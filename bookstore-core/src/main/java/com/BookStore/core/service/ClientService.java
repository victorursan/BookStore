package com.BookStore.core.service;

import com.BookStore.core.models.Client;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
public interface ClientService {
    List<Client> findAll();
}
