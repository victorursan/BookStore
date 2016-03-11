package com.BookStore.View;


import com.BookStore.Controller.Controller;
import com.BookStore.Model.Validators.ValidatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private Controller ctrl;

    public Console() {
        this.ctrl = new Controller();
    }

    public Console(Controller controller) {
        this.ctrl = controller;
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

    private Long readLong(String message) {
        String input = readString(message);
        try {
            return Long.parseLong(input, 10);
        } catch (NumberFormatException e) {
            return readLong(message);
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
        try {
            ctrl.addClient(readString("Enter first name: "), readString("Enter last name: "));
        } catch (ValidatorException e) {
            print("Data not valid. " + e.getMessage());

        }
    }

    private void addBook() {
        try {
            ctrl.addBook(readString("Enter title: "),
                    readString("Enter author:"),
                    readLong("Enter ISBN: "),
                    readString("Enter genre: "),
                    readString("Enter publisher: "),
                    readInteger("Enter price: "));
        } catch (ValidatorException e) {
            print("Data not valid. " + e.getMessage());
        }
    }

    private void updateClient() {
        try {
            ctrl.updateClient(readInteger("Id of client to update: "),
                    readString("Enter first name: "),
                    readString("Enter last name: "));
        } catch (ValidatorException e) {
            print("Data not valid. " + e.getMessage());
        }
    }

    private void updateBook() {
        try {
            ctrl.updateBook(readInteger("Id of client to update: "),
                    readString("Enter title: "),
                    readString("Enter author:"),
                    readLong("Enter ISBN: "),
                    readString("Enter genre: "),
                    readString("Enter publisher: "),
                    readInteger("Enter price: "),
                    readBool("Book availability: "));
        } catch (ValidatorException e) {
            print("Data not valid. " + e.getMessage());
        }
    }

    private void deleteClient() {
        ctrl.deleteClient(readInteger("Id of client to delete: "));
    }

    private void deleteBook() {
        ctrl.deleteClient(readInteger("Id of book to delete: "));
    }

    private void menu() {
        println("Options:" +
                "\n1. Add client" +
                "\n2. Add book" +
                "\n3. Delete client" +
                "\n4. Delete book" +
                        "\n5. Update client" +
                        "\n6. Update book" +
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
            case 5:
                updateClient();
                break;
            case 6:
                updateBook();
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
