package com.BookStore.web;

import com.BookStore.web.Models.Book;
import com.BookStore.web.Models.Client;

import java.util.List;

public interface ControllerService {

    String getAllOptions();

    void addClient(String firstName, String lastName);

    void addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price);

    void updateClient(Integer id, String firstName, String lastName);

    void deleteClient(Integer id);

    void updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price, Boolean available);

    void deleteBook(Integer id);

    List<Book> clientBooks(Integer id);

    Client clientWhoSpentMost();

    Client clientWithMostBooks();

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
