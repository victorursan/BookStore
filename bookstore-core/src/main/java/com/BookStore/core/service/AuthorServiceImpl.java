package com.BookStore.core.service;

import com.BookStore.core.models.Author;
import com.BookStore.core.repositories.AuthorRepository;
import com.BookStore.core.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by victor on 6/21/16.
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAllWithBooksSpring();
    }

    @Override
    @Transactional
    public Author updateAuthor(Integer authorId, String name, Set<Integer> books) {

        Author author = authorRepository.findOne(authorId);
        author.setName(name);
        author.setList_of_books(bookRepository.findAll(books.stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));

        return author;
    }

    @Override
    public Author createAuthor(String name) {
        return authorRepository.save(new Author(name));
    }

    @Override
    public void deleteAuthor(Integer authorId) {
        authorRepository.delete(authorId);
    }
}
