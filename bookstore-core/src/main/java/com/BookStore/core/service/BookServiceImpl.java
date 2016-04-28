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
    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
