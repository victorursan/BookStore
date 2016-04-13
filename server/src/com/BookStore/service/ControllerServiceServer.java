package com.BookStore.service;

import com.BookStore.ControllerService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by victor on 4/13/16.
 */
public class ControllerServiceServer implements ControllerService {
    private ExecutorService executorService;

    public ControllerServiceServer(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public CompletableFuture<String> sayHi(String name) {
        return CompletableFuture.supplyAsync(() -> "Hi" + name, executorService);
    }

    @Override
    public CompletableFuture<String> sayBye(String name) {
        return CompletableFuture.supplyAsync(() -> "Bye " + name, executorService);
    }
}