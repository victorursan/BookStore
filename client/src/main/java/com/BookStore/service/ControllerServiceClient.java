package com.BookStore.service;

import com.BookStore.ControllerService;
import com.BookStore.Message;
import com.BookStore.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by victor on 4/13/16.
 */
public class ControllerServiceClient implements ControllerService {
    private static String LINE_SEPARATOR = System.getProperty("line.separator");
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ControllerServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    private CompletableFuture<String> getResponseFromMessage(Message request) {
        return CompletableFuture.supplyAsync(tcpClient.sendAndReceive(request)::body, executorService);
    }

    @Override
    public CompletableFuture<String> getAllOptions() {
        return getResponseFromMessage(Message.builder(ControllerService.GET_ALL_OPTIONS, "Get all options"));
    }

    @Override
    public CompletableFuture<String> addClient(String firstName, String lastName) {
        return getResponseFromMessage(Message.builder(ControllerService.ADD_CLIENT, firstName + LINE_SEPARATOR + lastName));
    }

    @Override
    public CompletableFuture<String> addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price) {
        return getResponseFromMessage(Message.builder(ControllerService.ADD_BOOK,
                title + LINE_SEPARATOR + auth + LINE_SEPARATOR + isbn + LINE_SEPARATOR +
                        genre + LINE_SEPARATOR + publisher + LINE_SEPARATOR + price));
    }

    @Override
    public CompletableFuture<String> updateClient(Integer id, String firstName, String lastName) {
        return getResponseFromMessage(Message.builder(ControllerService.UPDATE_CLIENT, id + LINE_SEPARATOR +
                firstName + LINE_SEPARATOR + lastName));
    }

    @Override
    public CompletableFuture<String> deleteClient(Integer id) {
        return getResponseFromMessage(Message.builder(ControllerService.DELETE_CLIENT, id.toString()));
    }

    @Override
    public CompletableFuture<String> updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price, Boolean available) {
        return getResponseFromMessage(Message.builder(ControllerService.UPDATE_BOOK, id + LINE_SEPARATOR +
                title + LINE_SEPARATOR + auth + LINE_SEPARATOR + isbn + LINE_SEPARATOR +
                genre + LINE_SEPARATOR + publisher + LINE_SEPARATOR + price + LINE_SEPARATOR + available));
    }

    @Override
    public CompletableFuture<String> deleteBook(Integer id) {
        return getResponseFromMessage(Message.builder(ControllerService.DELETE_BOOK, id.toString()));
    }

    @Override
    public CompletableFuture<String> clientBooks(Integer id) {
        return getResponseFromMessage(Message.builder(ControllerService.CLIENT_BOOKS, id.toString()));
    }

    @Override
    public CompletableFuture<String> clientWhoSpentMost() {
        return getResponseFromMessage(Message.builder(ControllerService.MOST_SPENT, "client with most spent"));
    }

    @Override
    public CompletableFuture<String> clientWithMostBooks() {
        return getResponseFromMessage(Message.builder(ControllerService.MOST_BOOKS, "client with most books"));
    }

    @Override
    public CompletableFuture<String> getAllClients() {
        return getResponseFromMessage(Message.builder(ControllerService.ALL_CLIENTS, "all clients"));
    }

    @Override
    public CompletableFuture<String> getAllBooks() {
        return getResponseFromMessage(Message.builder(ControllerService.ALL_BOOKS, "all books"));
    }

    @Override
    public CompletableFuture<String> availableBooks() {
        return getResponseFromMessage(Message.builder(ControllerService.AVAILABLE_BOOKS, "all available books"));
    }

    @Override
    public CompletableFuture<String> filterBooksByGenre(String genre) {
        return getResponseFromMessage(Message.builder(ControllerService.GENRE_BOOKS, genre));
    }

    @Override
    public CompletableFuture<String> filterBooksByAuthor(String auth) {
        return getResponseFromMessage(Message.builder(ControllerService.AUTHOR_BOOKS, auth));
    }

    @Override
    public CompletableFuture<String> filterBooksCheaperThan(Integer price) {
        return getResponseFromMessage(Message.builder(ControllerService.CHEAP_BOOKS, price.toString()));
    }

    @Override
    public CompletableFuture<String> filterBooksMoreExpensiveThan(Integer price) {
        return getResponseFromMessage(Message.builder(ControllerService.EXPENSIVE_BOOKS, price.toString()));
    }

    @Override
    public CompletableFuture<String> returnBook(Integer clientId, Integer bookId) {
        return getResponseFromMessage(Message.builder(ControllerService.RETURN_BOOK, clientId + LINE_SEPARATOR + bookId));
    }

    @Override
    public CompletableFuture<String> buyBook(Integer clientId, Integer bookId) {
        return getResponseFromMessage(Message.builder(ControllerService.BUY_BOOK, clientId + LINE_SEPARATOR + bookId));
    }


}
