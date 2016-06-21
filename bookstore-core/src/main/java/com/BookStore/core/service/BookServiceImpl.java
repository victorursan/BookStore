package com.BookStore.core.service;

import com.BookStore.core.models.Book;
import com.BookStore.core.repositories.AuthorRepository;
import com.BookStore.core.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<Book> findAll() {
        log.trace("findAll()");
        List<Book> bookList= bookRepository.findAll();
        log.trace("findAll: books={}", bookList);
        return bookList;
    }

    @Override
    @Transactional
    public Book updateBook(Integer bookId, String title, Long ISBN, Integer year, Integer authorId) {
        Book book = bookRepository.findOne(bookId);
        book.setTitle(title);
        book.setAuthor(authorRepository.getOne(authorId));
        book.setIsbn(ISBN);
        book.setYear(year);
        return book;
    }

    @Override
    public Book createBook(String title, Long ISBN, Integer year, Integer authorId) {
        return bookRepository.save(new Book(title,ISBN, year, authorRepository.getOne(authorId)));
    }


    @Override
    public void deleteBook(Integer bookId) {
        log.trace("deleteBook: bookId = {}", bookId);
        bookRepository.delete(bookId);
    }

}
