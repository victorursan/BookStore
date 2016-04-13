package com.BookStore;

import java.util.concurrent.CompletableFuture;

/**
 * Created by victor on 4/13/16.
 */
public interface ControllerService {
    String SERVICE_HOST = "localhost";
    int SERVICE_PORT = 5000;

    String SAY_HI = "sayHi";
    String SAY_BYE = "sayBye";

    CompletableFuture<String> sayHi(String name);
    CompletableFuture<String> sayBye(String name);
}
