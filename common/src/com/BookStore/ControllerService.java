package com.BookStore;

import java.util.concurrent.CompletableFuture;

/**
 * Created by victor on 4/13/16.
 */

public interface ControllerService {
    String SERVICE_HOST = "localhost";
    int SERVICE_PORT = 5000;

    String GET_ALL_OPTIONS = "getAllOptions";
    String ADD_CLIENT = "addClient";

    CompletableFuture<String> getAllOptions();

    CompletableFuture addClient(String firstName, String lastName);
}
