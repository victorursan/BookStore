package com.BookStore.core.service;

import com.BookStore.core.models.Book;
import com.BookStore.core.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by victor on 4/28/16.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book updateBook(Integer bookId, String title, String author, Long ISBN, String genre, String publisher, Integer price, Boolean available) {
        Book book = bookRepository.findOne(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setISBN(ISBN);
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
