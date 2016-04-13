package com.BookStore.service;

import com.BookStore.Controller.Controller;
import com.BookStore.ControllerService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

/**
 * Created by victor on 4/13/16.
 */
public class ControllerServiceServer implements ControllerService {
    private ExecutorService executorService;
    private Controller ctrl;

    public ControllerServiceServer(ExecutorService executorService, Controller ctrl) {
        this.executorService = executorService;
        this.ctrl = ctrl;
    }

    private <T> CompletableFuture<T> sendRequestWithMessage(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, executorService);
    }

    @Override
    public CompletableFuture<String> getAllOptions() {
        return CompletableFuture.supplyAsync(() -> "Options:" +
                        "\n1. Add client" +
                        "\n2. Add book" +
                        "\n3. Delete client" +
                        "\n4. Delete book" +
                        "\n5. Update client" +
                        "\n6. Update book" +
                        "\n7. Show all clients" +
                        "\n8. Show all books" +
                        "\n9. Show available books" +
                        "\n10. Books of a genre" +
                        "\n11. Books by an author" +
                        "\n12. Books cheaper than" +
                        "\n13. Books more expensive than" +
                        "\n14. Client with most books" +
                        "\n15. Client who spent most" +
                        "\n16. Client purchase" +
                        "\n17. Client return" +
                        "\n18. Purchases by one client" +
                        "\n0. Exit"
                , executorService);
    }

    @Override
    public CompletableFuture addClient(String firstName, String lastName) {
        return sendRequestWithMessage(() -> {
            ctrl.addClient(firstName, lastName);
            return null;
        });
    }
}