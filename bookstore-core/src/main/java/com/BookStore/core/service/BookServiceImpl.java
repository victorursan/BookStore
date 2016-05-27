package com.BookStore.core.service;

import com.BookStore.core.models.Book;
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

    @Override
    public List<Book> findAll() {
        List<Book> bookList= bookRepository.findAll();
        log.trace("findAll: books={}", bookList);
        System.out.println(bookList.toString());
        return bookList;
    }

    @Override
    @Transactional
    public Book updateBook(Integer bookId, String title, String author, Long isbn, String genre, String publisher, Integer price, Boolean available) {
        Book book = bookRepository.findOne(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setGenre(genre);
        book.setPublisher(publisher);
        book.setPrice(price);
        book.setAvailable(available);
        return book;
    }

    @Override
    public Book createBook(String title, String author, Long ISBN, String genre, String publisher, Integer price, Boolean available) {
        return bookRepository.save(new Book(title, author, ISBN, genre, publisher, price, available));
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.delete(bookId);
    }

}
