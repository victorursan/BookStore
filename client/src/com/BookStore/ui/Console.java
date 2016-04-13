package com.BookStore.ui;

import com.BookStore.ControllerService;

import java.util.concurrent.CompletableFuture;

/**
 * Created by victor on 4/13/16.
 */
public class Console {
    private ControllerService controller;


    public Console(ControllerService controller) {
        this.controller = controller;
    }

    public void run() {
        // TODO show menu

        sayHi();
        sayBye();
    }

    private void sayHi() {
        CompletableFuture<String> result = controller.sayHi("John");

        result.handle((String message, Throwable error) -> {
           if (error != null) {
               error.printStackTrace();
           } else {
               System.out.println(message);
           }
            return this;
        });
    }

    private void sayBye() {
        CompletableFuture<String> result = controller.sayBye("Mary");
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                System.out.println(message);
            }
            return this;
        });
    }

}
