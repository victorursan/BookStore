package com.BookStore.ui;

import com.BookStore.service.ControllerServiceClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    private <E> void printEntityList(List<E> elements) {
        elements.forEach(this::println);
    }

    private static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        iter.forEach(list::add);
        return list;
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
        printEntityList(makeCollection(controller.getAllClients()));
        Integer id = readInteger("Id of client to remove: ");
        controller.deleteClient(id);
    }

    private void deleteBook() {
        printEntityList(makeCollection(controller.getAllBooks()));
        Integer id = readInteger("Id of book to remove: ");
        controller.deleteBook(id);
    }

    private void clientBooks() {
        printEntityList(makeCollection(controller.getAllClients()));
        Integer id = readInteger("Display books for client: ");
        printEntityList(controller.clientBooks(id));
    }

    private void clientReturnMode() {
        printEntityList(makeCollection(controller.getAllClients()));
        Integer client = readInteger("Which client wants to return?");
        printEntityList(controller.clientBooks(client));
        Integer book = readInteger("Which book is wanted for return?");
        controller.returnBook(client, book);
    }

    private void clientBuyMode() {
        printEntityList(makeCollection(controller.getAllClients()));
        Integer client = readInteger("Which client wants to purchase? ");
        printEntityList(controller.availableBooks());
        Integer book = readInteger("Which book is wanted for buying? ");
        controller.buyBook(client, book);
    }

    private void mostMoneyClient() {
        println(controller.clientWhoSpentMost());
    }

    private void mostBooksClient() {
        println(controller.clientWithMostBooks());
    }

    private void expensiveBooks() {
        Integer price = readInteger("Books more expensive than: ");
        printEntityList(controller.filterBooksMoreExpensiveThan(price));
    }

    private void cheaperBooks() {
        Integer price = readInteger("Books cheaper than: ");
        printEntityList(controller.filterBooksCheaperThan(price));
    }

    private void authorBooks() {
        String auth = readString("Enter author:");
        printEntityList(controller.filterBooksByAuthor(auth));
    }

    private void genreBooks() {
        String genre = readString("Enter genre:");
        printEntityList(controller.filterBooksByGenre(genre));
    }

    private void showAvailableBooks() {
        printEntityList(controller.availableBooks());
    }

    private void showAllBooks() {
        printEntityList(makeCollection(controller.getAllBooks()));
    }

    private void showAllClients() {
        printEntityList(makeCollection(controller.getAllClients()));
    }

    private void getAllOptions() {
        println(controller.getAllOptions());
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

