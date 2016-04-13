package com.BookStore;

import java.util.concurrent.CompletableFuture;

/**
 * Created by victor on 4/13/16.
 */

public interface ControllerService {
    String SERVICE_HOST = "localhost";
    int SERVICE_PORT = 5000;

    String GET_ALL_OPTIONS = "getAllOptions";
    String ADD_CLIENT = "addClient";
    String UPDATE_CLIENT = "updateClient";
    String DELETE_CLIENT = "deleteClient";
    String ADD_BOOK = "addBook";
    String UPDATE_BOOK = "updateBook";
    String DELETE_BOOK = "deleteBook";
    String CLIENT_BOOKS = "clientBooks";
    String MOST_SPENT = "clientWhoSpentMost";
    String MOST_BOOKS = "clientWithMostBooks";
    String ALL_CLIENTS = "getAllClients";
    String ALL_BOOKS = "getAllBooks";
    String AVAILABLE_BOOKS = "availableBooks";
    String GENRE_BOOKS = "filterBooksByGenre";
    String AUTHOR_BOOKS = "filterBooksByAuthor";
    String CHEAP_BOOKS = "filterBooksCheaperThan";
    String EXPENSIVE_BOOKS = "filterBooksMoreExpensiveThan";

    CompletableFuture<String> getAllOptions();

    CompletableFuture addClient(String firstName, String lastName);

    CompletableFuture addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price);

    CompletableFuture updateClient(Integer id, String firstName, String lastName);

    CompletableFuture deleteClient(Integer id);

    CompletableFuture updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price);

    CompletableFuture deleteBook(Integer id);

    CompletableFuture<String> clientBooks(Integer id);

    CompletableFuture<String> clientWhoSpentMost();

    CompletableFuture<String> clientWithMostBooks();

    CompletableFuture<String> getAllClients();

    CompletableFuture<String> getAllBooks();

    CompletableFuture<String> availableBooks();

    CompletableFuture<String> filterBooksByGenre(String genre);

    CompletableFuture<String> filterBooksByAuthor(String auth);

    CompletableFuture<String> filterBooksCheaperThan(Integer price);

    CompletableFuture<String> filterBooksMoreExpensiveThan(Integer price);
}
