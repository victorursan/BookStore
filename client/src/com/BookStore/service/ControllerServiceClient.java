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
        return getResponseFromMessage(Message.builder(ControllerService.GET_ALL_OPTIONS, "get all options"));
    }

    @Override
    public CompletableFuture addClient(String firstName, String lastName) {
        return getResponseFromMessage(Message.builder(ControllerService.ADD_CLIENT, firstName + LINE_SEPARATOR + lastName));
    }

    @Override
    public CompletableFuture addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price) {
        return getResponseFromMessage(Message.builder(ControllerService.ADD_BOOK,
                title + LINE_SEPARATOR + auth + LINE_SEPARATOR + isbn + LINE_SEPARATOR +
                        genre + LINE_SEPARATOR + publisher + LINE_SEPARATOR + price));
    }

    @Override
    public CompletableFuture updateClient(Integer id, String firstName, String lastName) {
        return getResponseFromMessage(Message.builder(ControllerService.UPDATE_CLIENT, id + LINE_SEPARATOR +
                firstName + LINE_SEPARATOR + lastName));
    }

    @Override
    public CompletableFuture deleteClient(Integer id) {
        return getResponseFromMessage(Message.builder(ControllerService.DELETE_CLIENT, id.toString()));
    }

    @Override
    public CompletableFuture updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price) {
        return getResponseFromMessage(Message.builder(ControllerService.UPDATE_BOOK, id + LINE_SEPARATOR +
                title + LINE_SEPARATOR + auth + LINE_SEPARATOR + isbn + LINE_SEPARATOR +
                genre + LINE_SEPARATOR + publisher + LINE_SEPARATOR + price));
    }

    @Override
    public CompletableFuture deleteBook(Integer id) {
        return getResponseFromMessage(Message.builder(ControllerService.DELETE_BOOK, id.toString()));
    }


}