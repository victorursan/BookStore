package com.BookStore.service;

import com.BookStore.ControllerService;
import com.BookStore.Models.Book;
import com.BookStore.Models.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ControllerServiceClient implements ControllerService {

    @Autowired
    private ControllerService controllerService;

    public ControllerServiceClient() {
    }

    public void setControllerService(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @Override
    public String getAllOptions() {
        return controllerService.getAllOptions();
    }

    @Override
    public void addClient(String firstName, String lastName) {
        controllerService.addClient(firstName, lastName);
    }

    @Override
    public void addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price) {
        controllerService.addBook(title, auth, isbn, genre, publisher, price);
    }

    @Override
    public void updateClient(Integer id, String firstName, String lastName) {
        controllerService.updateClient(id, firstName, lastName);
    }

    @Override
    public void deleteClient(Integer id) {
        controllerService.deleteClient(id);
    }

    @Override
    public void updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price, Boolean available) {
        controllerService.updateBook(id, title, auth, isbn, genre, publisher, price, available);
    }

    @Override
    public void deleteBook(Integer id) {
        controllerService.deleteBook(id);
    }

    @Override
    public List<Book> clientBooks(Integer id) {
        return controllerService.clientBooks(id);
    }

    @Override
    public Client clientWhoSpentMost() {
        return controllerService.clientWithMostBooks();
    }

    @Override
    public Client clientWithMostBooks() {
        return controllerService.clientWithMostBooks();
    }

    @Override
    public Iterable<Client> getAllClients() {
        return controllerService.getAllClients();
    }

    @Override
    public Iterable<Book> getAllBooks() {
        return controllerService.getAllBooks();
    }

    @Override
    public List<Book> availableBooks() {
        return controllerService.availableBooks();
    }

    @Override
    public List<Book> filterBooksByGenre(String genre) {
        return controllerService.filterBooksByGenre(genre);
    }

    @Override
    public List<Book> filterBooksByAuthor(String auth) {
        return controllerService.filterBooksByAuthor(auth);
    }

    @Override
    public List<Book> filterBooksCheaperThan(Integer price) {
        return controllerService.filterBooksCheaperThan(price);
    }

    @Override
    public List<Book> filterBooksMoreExpensiveThan(Integer price) {
        return controllerService.filterBooksMoreExpensiveThan(price);
    }

    @Override
    public void returnBook(Integer clientId, Integer bookId) {
        controllerService.returnBook(clientId, bookId);
    }

    @Override
    public void buyBook(Integer clientId, Integer bookId) {
        controllerService.buyBook(clientId, bookId);
    }

}
