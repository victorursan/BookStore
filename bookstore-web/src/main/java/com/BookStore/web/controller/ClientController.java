package com.BookStore.web.controller;

import com.BookStore.core.models.Client;
import com.BookStore.service.ClientService;
import com.BookStore.web.dto.ClientsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientsDto getBooks() {

        List<Client> disciplineList = clientService.findAll();

        return new ClientsDto(disciplineList);
    }
}
