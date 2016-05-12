package com.BookStore.web.controller;

import com.BookStore.core.models.Book;
import com.BookStore.core.service.BookService;
import com.BookStore.web.dto.BookDto;
import com.BookStore.web.dto.BooksDto;
import com.BookStore.web.dto.EmptyJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 4/28/16.
 */

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public BooksDto getBooks() {

        List<Book> disciplineList = bookService.findAll();

        return new BooksDto(disciplineList);
    }
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.PUT, consumes = "application/vnd.api+json")
    public Map<String, BookDto> updateBook(@PathVariable final Integer bookId,
                                             @RequestBody final BookDto bookDto) {

        Book book = bookService.updateBook(bookId, bookDto.getTitle(), bookDto.getAuthor(), bookDto.getISBN(), bookDto.getGenre(),
                bookDto.getPublisher(), bookDto.getPrice(), bookDto.isAvailable());

        Map<String, BookDto> bookDtoMap = new HashMap<>();
        bookDtoMap.put("book", new BookDto(book));

        return bookDtoMap;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, consumes = "application/vnd.api+json", produces = "application/vnd.api+json")
    public Map<String, BookDto> createBook(@RequestBody final BookDto bookDto) {

        Book book = bookService.createBook(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getISBN(), bookDto.getGenre(),
                bookDto.getPublisher(), bookDto.getPrice(), bookDto.isAvailable());

        Map<String, BookDto> bookDtoMap = new HashMap<>();
        bookDtoMap.put("book", new BookDto(book));

        return bookDtoMap;
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE, consumes = "application/vnd.api+json")
    public ResponseEntity deleteBook(@PathVariable final Integer bookId) {

        bookService.deleteBook(bookId);
        //todo catch errors
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

}
