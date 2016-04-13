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
        controller.updateBook(id, title, auth, isbn, genre, publisher, price);
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
        CompletableFuture<String> result = controller.clientBooks(id);
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
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
        controller.clientWhoSpentMost();
    }

    private void mostBooksClient() {
        controller.clientWithMostBooks();
    }

    private void expensiveBooks() {
        Integer price = readInteger("Books more expensive than: ");
        CompletableFuture<String> result = controller.filterBooksMoreExpensiveThan(price);
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
    }

    private void cheaperBooks() {
        Integer price = readInteger("Books cheaper than: ");
        CompletableFuture<String> result = controller.filterBooksCheaperThan(price);
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
    }

    private void authorBooks() {
        String auth = readString("Enter author:");
        CompletableFuture<String> result = controller.filterBooksByAuthor(auth);
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
    }

    private void genreBooks() {
        String genre = readString("Enter genre:");
        CompletableFuture<String> result = controller.filterBooksByGenre(genre);
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
    }

    private void showAvailableBooks() {
        CompletableFuture<String> result = controller.availableBooks();
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
    }

    private void showAllBooks() {
        CompletableFuture<String> result = controller.getAllBooks();
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
    }

    private void showAllClients() {
        CompletableFuture<String> result = controller.getAllClients();
        result.handle((String message, Throwable error) -> {
            if (error != null) {
                error.printStackTrace();
            } else {
                println(message);
            }
            return this;
        });
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

