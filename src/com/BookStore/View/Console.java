package com.BookStore.View;


import com.BookStore.Controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private Controller controller;

    public Console() {
        this.controller = new Controller();
    }

    public Console(Controller controller) {
        this.controller = controller;
    }

    private <T> void print(T message) {
        System.out.print(message);
    }

    private <T> void println(T message) {
        System.out.println(message);
    }

    private String readString(String message) {
        try {
            print(message);
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            return bufferRead.readLine();
        } catch (IOException e) {
            print(e.getMessage());
            return readString(message);
        }
    }

    private Integer readInteger(String message) {
        String input = readString(message);
        try {
            return Integer.parseInt(input, 10);
        } catch (NumberFormatException e) {
            return readInteger(message);
        }
    }

    private Boolean readBool(String message) {
        String input = readString(message);
        try {
            return Boolean.parseBoolean(input);
        } catch (NumberFormatException e) {
            return readBool(message);
        }
    }

    private void addClient() {

    }

    private void addBook() {

    }

    private void deleteClient() {

    }

    private void deleteBook() {

    }

    private void menu() {
        println("Options:" +
                "\n1. Add client" +
                "\n2. Add book" +
                "\n3. Delete client" +
                "\n4. Delete book" +
                "\n0. Exit"
        );
        Integer option = readInteger("Option: ");
        switch (option) {
            case 1: addClient();
                break;
            case 2: addBook();
                break;
            case 3: deleteClient();
                break;
            case 4: deleteBook();
                break;
            case 0: return;
            default: println("Invalid option, try again.");
        }
        menu();
    }

    public void run() {
        menu();
    }
}
