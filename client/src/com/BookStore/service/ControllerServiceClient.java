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
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ControllerServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> sayHi(String name) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(ControllerService.SAY_HI, name);
            Message response = tcpClient.sendAndReceive(request);
            return response.body();} ,executorService);
    }

    @Override
    public CompletableFuture<String> sayBye(String name) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(ControllerService.SAY_BYE, name);
            Message response = tcpClient.sendAndReceive(request);
            return response.body();} ,executorService);
    }
}
