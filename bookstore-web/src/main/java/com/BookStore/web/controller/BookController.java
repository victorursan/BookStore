package com.BookStore.web.controller;

import com.BookStore.core.models.Book;
import com.BookStore.core.service.BookService;
import com.BookStore.web.converter.BookConverter;
import com.BookStore.web.dto.BookDto;
import com.BookStore.web.dto.BooksDataDto;
import com.BookStore.web.dto.EmptyJsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by victor on 4/28/16.
 */

@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public BooksDataDto getBooks() {
        log.trace("getBooks");
        List<Book> bookList = bookService.findAll();
        log.trace("getBooks: books = {}", bookList);

        Set<BookDto> bookDtos = bookConverter.convertModelsToDtos(bookList);

        return new BooksDataDto(bookDtos);
    }
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.PUT, consumes = "application/vnd.api+json")
    public Map<String, BookDto> updateBook(@PathVariable final Integer bookId, @RequestBody final Map<String, BookDto> bookRequestDtoMap) {
        log.trace("updateBook: bookRequestDtoMap = {} ", bookRequestDtoMap);
        BookDto bookDto = bookRequestDtoMap.get("book");
        Book book = bookService.updateBook(bookId, bookDto.getTitle(), bookDto.getAuthor(), bookDto.getIsbn(), bookDto.getGenre(),
                bookDto.getPublisher(), bookDto.getPrice(), bookDto.getAvailable());
        log.trace("updateBook: book = {} ", book);
        Map<String, BookDto> bookDtoMapResult = new HashMap<>();
        bookDtoMapResult.put("book", bookConverter.convertModelToDto(book));

        return bookDtoMapResult;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, consumes = "application/vnd.api+json", produces = "application/vnd.api+json")
    public Map<String, BookDto> createBook(@RequestBody final Map<String, BookDto> bookRequestDtoMap) {
        BookDto bookDto = bookRequestDtoMap.get("book");
        log.trace("createBook: bookRequestDtoMap = {}", bookRequestDtoMap);
        Book book = bookService.createBook(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getIsbn(), bookDto.getGenre(),
                bookDto.getPublisher(), bookDto.getPrice(), bookDto.getAvailable());
        log.trace("createBook: book = {}", book);

        Map<String, BookDto> bookDtoMap = new HashMap<>();
        bookDtoMap.put("book", bookConverter.convertModelToDto(book));

        return bookDtoMap;
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE, consumes = "application/vnd.api+json")
    public ResponseEntity deleteBook(@PathVariable final Integer bookId) {
        log.trace("deleteBook: bookId = {}", bookId);
        bookService.deleteBook(bookId);
        //todo catch errors
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

}
