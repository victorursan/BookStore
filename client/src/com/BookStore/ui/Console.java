package com.BookStore.ui;

import com.BookStore.ControllerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//        try {
//            ctrl.addClient(readString("Enter first name: "), readString("Enter last name: "));
//        } catch (BaseException e) {
//            println("Data not valid: " + e.getMessage());
//        }
    }

    private void addBook() {
//        try {
//            ctrl.addBook(readString("Enter title: "),
//                    readString("Enter author:"),
//                    readLong("Enter ISBN: "),
//                    readString("Enter genre: "),
//                    readString("Enter publisher: "),
//                    readInteger("Enter price: "));
//        } catch (BaseException e) {
//            println("Data not valid: " + e.getMessage());
//        }
    }

    private void updateClient() {
//        try {
//            ctrl.updateClient(readInteger("Id of client to update: "),
//                    readString("Enter first name: "),
//                    readString("Enter last name: "));
//        } catch (BaseException e) {
//            println("Data not valid: " + e.getMessage());
//        }
    }

    private void updateBook() {
//        try {
//            ctrl.updateBook(readInteger("Id of book to update: "),
//                    readString("Enter title: "),
//                    readString("Enter author:"),
//                    readLong("Enter ISBN: "),
//                    readString("Enter genre: "),
//                    readString("Enter publisher: "),
//                    readInteger("Enter price: "),
//                    readBool("Book availability: "));
//        } catch (BaseException e) {
//            println("Data not valid. " + e.getMessage());
//        }
    }

    private void deleteClient() {
//        try {
//            ctrl.deleteClient(readInteger("Id of client to delete: "));
//        } catch (BaseException e) {
//            println("Data not valid. " + e.getMessage());
//        }
    }

    private void deleteBook() {
//        try {
//            ctrl.deleteBook(readInteger("Id of book to delete: "));
//        } catch (BaseException e) {
//            println("Data not valid. " + e.getMessage());
//        }
    }

    private void clientBooks() {
//        int clientOpt = readInteger("Display books for client: ");
//        ctrl.clientBooks(clientOpt).ifPresent(books -> books.forEach(this::println));
    }

    private void clientReturnMode() {
//        ctrl.getAllClients().forEach(this::println);
//        int clientOpt = readInteger("Which client wants to return?");
//        ctrl.clientBooks(clientOpt).ifPresent(books -> books.forEach(this::println));
//        int bookOpt = readInteger("Which book is wanted for return?");
//        try {
//            ctrl.returnBook(clientOpt, bookOpt);
//            println("Book returned!");
//        } catch (BaseException e) {
//            println("Data not valid. " + e.getMessage());
//        }
    }

    private void clientBuyMode() {
//        ctrl.getAllClients().forEach(this::println);
//        int clientOpt = readInteger("Which client wants to purchase? ");
//        ctrl.availableBooks().forEach(this::println);
//        int bookOpt = readInteger("Which book is wanted for buying? ");
//        try {
//            ctrl.buyBook(clientOpt, bookOpt);
//            println("Book bought!");
//        } catch (BaseException e) {
//            println("Data not valid. " + e.getMessage());
//        }
    }

    private void mostMoneyClient() {
//        ctrl.clientWhoSpentMost().ifPresent(this::println);
    }

    private void mostBooksClient() {
//        ctrl.clientWithMostBooks().ifPresent(this::println);
    }

    private void expensiveBooks() {
//        int value = readInteger("Display all books with a price higher than: ");
//        ctrl.filterBooksMoreExpensiveThan(value).forEach(this::println);
    }

    private void cheaperBooks() {
//        int value = readInteger("Display all books with a price lower than: ");
//        ctrl.filterBooksCheaperThan(value).forEach(this::println);
    }

    private void authorBooks() {
//        String author = readString("Give an author to search for: ");
//        ctrl.filterBooksByAuthor(author).forEach(this::println);
    }

    private void genreBooks() {
//        String genre = readString("Give an genre to search for: ");
//        ctrl.filterBooksByGenre(genre).forEach(this::println);
    }

    private void showAvailableBooks() {
//        ctrl.availableBooks().forEach(this::println);
    }

    private void showAllBooks() {
//        ctrl.getAllBooks().forEach(this::println);
    }

    private void showAllClients() {
//        ctrl.getAllClients().forEach(this::println);
    }

    private void getAllOptions() {
        CompletableFuture<String> result = controller.getAllOptions();
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });

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

