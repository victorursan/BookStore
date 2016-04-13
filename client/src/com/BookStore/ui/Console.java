package com.BookStore.ui;

import com.BookStore.ControllerService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        Future<String> result = controller.sayHi("John"); // non-blocking

        // do stuff...

        try {
            System.out.println(result.get()); // blocking
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void sayBye() {
        Future<String> result = controller.sayBye("Mary"); // non-blocking

        // do stuff...

        try {
            System.out.println(result.get()); // blocking
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
