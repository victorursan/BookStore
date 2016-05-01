package com.BookStore.web.controller;

import com.BookStore.core.models.Book;
import com.BookStore.core.service.BookService;
import com.BookStore.web.dto.BooksDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public
    @ResponseBody
    BooksDto getBooks() {

        List<Book> disciplineList = bookService.findAll();

        return new BooksDto(disciplineList);
    }

}