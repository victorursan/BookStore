package com.BookStore.core.service;

import com.BookStore.core.models.Author;

import java.util.List;
import java.util.Set;

/**
 * Created by victor on 6/21/16.
 */

public interface AuthorService {

    List<Author> findAll();

    Author updateAuthor(Integer authorId, String name, Set<Integer> books);

    Author createAuthor(String name);

    void deleteAuthor(Integer authorId);
}
