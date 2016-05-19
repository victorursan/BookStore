package com.BookStore.web.controller;

import com.BookStore.core.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by victor on 4/28/16.
 */

@RestController
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;
}
