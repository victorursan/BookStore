package com.BookStore.ui;

import com.BookStore.service.ControllerServiceClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by victor on 4/13/16.
 */
public class Console {
    private ControllerServiceClient controller;


    public Console(ControllerServiceClient controller) {
        this.controller = controller;
    }

    public void run() {
        getAllOptions();
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
            println(e.getMessage());
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
        String firstName = readString("Enter first name: ");
        String lastName = readString("Enter last name:");
        controller.addClient(firstName, lastName);
    }

    private void addBook() {
        String title = readString("Enter title: ");
        String auth = readString("Enter author:");
        Long isbn = readLong("Enter ISBN: ");
        String genre = readString("Enter genre: ");
        String publisher = readString("Enter publisher: ");
        Integer price = readInteger("Enter price: ");
        controller.addBook(title, auth, isbn, genre, publisher, price);
    }

    private void updateClient() {
        Integer id = readInteger("Id of client to update: ");
        String firstName = readString("Enter first name: ");
        String lastName = readString("Enter last name:");
        controller.updateClient(id, firstName, lastName);
    }

    private void updateBook() {
        Integer id = readInteger("Id of book to update: ");
        String title = readString("Enter title: ");
        String auth = readString("Enter author:");
        Long isbn = readLong("Enter ISBN: ");
        String genre = readString("Enter genre: ");
        String publisher = readString("Enter publisher: ");
        Integer price = readInteger("Enter price: ");
        Boolean available = readBool("Is Available (true/ false):");
        controller.updateBook(id, title, auth, isbn, genre, publisher, price, available);
    }

    private void deleteClient() {
        Integer id = readInteger("Id of client to remove: ");
        controller.deleteClient(id);
    }

    private void deleteBook() {
        Integer id = readInteger("Id of book to remove: ");
        controller.deleteBook(id);
    }

    private void clientBooks() {
        Integer id = readInteger("Display books for client: ");
        print(controller.clientBooks(id));
    }

    private void clientReturnMode() {
        print(controller.getAllClients());
        Integer client = readInteger("Which client wants to return?");
        print(controller.clientBooks(client));
        Integer book = readInteger("Which book is wanted for return?");
        controller.returnBook(client, book);
    }

    private void clientBuyMode() {
        print(controller.getAllClients());
        Integer client = readInteger("Which client wants to purchase? ");
        print(controller.availableBooks());
        Integer book = readInteger("Which book is wanted for buying? ");
        controller.buyBook(client, book);
    }

    private void mostMoneyClient() {
        print(controller.clientWhoSpentMost());
    }

    private void mostBooksClient() {
        print(controller.clientWithMostBooks());
    }

    private void expensiveBooks() {
        Integer price = readInteger("Books more expensive than: ");
        print(controller.filterBooksMoreExpensiveThan(price));
    }

    private void cheaperBooks() {
        Integer price = readInteger("Books cheaper than: ");
        print(controller.filterBooksCheaperThan(price));
    }

    private void authorBooks() {
        String auth = readString("Enter author:");
        print(controller.filterBooksByAuthor(auth));
    }

    private void genreBooks() {
        String genre = readString("Enter genre:");
        print(controller.filterBooksByGenre(genre));
    }

    private void showAvailableBooks() {
        print(controller.availableBooks());
    }

    private void showAllBooks() {
        print(controller.getAllBooks());
    }

    private void showAllClients() {
        print(controller.getAllClients());
    }

    private void getAllOptions() {
        print(controller.getAllOptions());
        Integer option = readInteger("Option: ");
        switch (option) {
            case 1:
                addClient();
                break;
            case 2:
                addBook();
                break;
            case 3:
                deleteClient();
                break;
            case 4:
                deleteBook();
                break;
            case 5:
                updateClient();
                break;
            case 6:
                updateBook();
                break;
            case 7:
                showAllClients();
                break;
            case 8:
                showAllBooks();
                break;
            case 9:
                showAvailableBooks();
                break;
            case 10:
                genreBooks();
                break;
            case 11:
                authorBooks();
                break;
            case 12:
                cheaperBooks();
                break;
            case 13:
                expensiveBooks();
                break;
            case 14:
                mostBooksClient();
                break;
            case 15:
                mostMoneyClient();
                break;
            case 16:
                clientBuyMode();
                break;
            case 17:
                clientReturnMode();
                break;
            case 18:
                clientBooks();
                break;
            case 0:
                return;
            default:
                println("Invalid option, try again.");
        }
        getAllOptions();
    }

}

