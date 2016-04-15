package com.BookStore;

import com.BookStore.Models.Book;
import com.BookStore.Models.Client;

import java.util.List;
import java.util.Optional;

/**
 * Created by victor on 4/13/16.
 */

public interface ControllerService {

    String getAllOptions();

    void addClient(String firstName, String lastName);

    void addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price);

    void updateClient(Integer id, String firstName, String lastName);

    void deleteClient(Integer id);

    void updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price, Boolean available);

    void deleteBook(Integer id);

    Optional<List<Book>> clientBooks(Integer id);

    Optional<Client> clientWhoSpentMost();

    Optional<Client> clientWithMostBooks();

    Iterable<Client> getAllClients();

    Iterable<Book> getAllBooks();

    List<Book> availableBooks();

    List<Book> filterBooksByGenre(String genre);

    List<Book> filterBooksByAuthor(String auth);

    List<Book> filterBooksCheaperThan(Integer price);

    List<Book> filterBooksMoreExpensiveThan(Integer price);

    void returnBook(Integer clientId, Integer bookId);

    void buyBook(Integer clientId, Integer bookId);
}
