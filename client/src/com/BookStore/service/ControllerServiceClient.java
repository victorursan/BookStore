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


}
