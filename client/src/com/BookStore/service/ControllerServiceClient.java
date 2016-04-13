package com.BookStore.service;

import com.BookStore.ControllerService;
import com.BookStore.Message;
import com.BookStore.tcp.TcpClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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
    public Future<String> sayHi(String name) {
        Future<String> result = executorService.submit(() -> {
            Message request = new Message(ControllerService.SAY_HI, name);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    @Override
    public Future<String> sayBye(String name) {
        Future<String> result = executorService.submit(() -> {
            Message request = new Message(ControllerService.SAY_BYE, name);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }
}
