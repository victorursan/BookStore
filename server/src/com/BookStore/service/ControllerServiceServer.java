package com.BookStore.service;

import com.BookStore.ControllerService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by victor on 4/13/16.
 */
public class ControllerServiceServer implements ControllerService {
    private ExecutorService executorService;

    public ControllerServiceServer(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> sayHi(String name) {
        Future<String> result = executorService.submit(() -> "Hi " + name);
        return result;
    }

    @Override
    public Future<String> sayBye(String name) {
        Future<String> result = executorService.submit(() -> "Bye " + name);
        return result;
    }
}