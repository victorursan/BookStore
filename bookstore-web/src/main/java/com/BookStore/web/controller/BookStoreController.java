package com.BookStore.web.controller;

import com.BookStore.core.models.Client;
import com.BookStore.core.service.BookStoreService;
import com.BookStore.web.converter.ClientConverter;
import com.BookStore.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by victor on 4/28/16.
 */

@RestController
public class BookStoreController {

    private static final Logger log = LoggerFactory.getLogger(BookStoreController.class);

    @Autowired
    private BookStoreService bookStoreService;


    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/bookStore", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public ClientDto clientThatSpentMost() {
        log.trace("clientThatSpentMost");
        Client client = bookStoreService.clientThatSpentMost();
        log.trace("clientThatSpentMost: client = {}", client);
        return clientConverter.convertModelToDto(client);
    }
}
