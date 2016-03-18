package com.BookStore.View;


import com.BookStore.Controller.Controller;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.ValidatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

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

    private void clientBooks() {
        int clientOpt = readInteger("Display books for client: ");
        ctrl.clientBooks(clientOpt).ifPresent(books -> books.forEach(this::println));
    }

    private void clientReturnMode() {
        ctrl.getAllClients().forEach(this::println);
        int clientOpt = readInteger("Which client wants to return?");
        ctrl.clientBooks(clientOpt).ifPresent(books -> books.forEach(this::println));
        int bookOpt = readInteger("Which book is wanted for return?");
        ctrl.returnBook(clientOpt, bookOpt);
        println("Book returned!");
    }

    private void clientBuyMode() {
        ctrl.getAllClients().forEach(this::println);
        int clientOpt = readInteger("Which client wants to purchase? ");
        ctrl.availableBooks().forEach(this::println);
        int bookOpt = readInteger("Which book is wanted for buying? ");
        ctrl.buyBook(clientOpt, bookOpt);
        println("Book bought!");
    }

    private void mostMoneyClient() {
        ctrl.clientWhoSpentMost().ifPresent(this::println);
    }

    private void mostBooksClient() {
        ctrl.clientWithMostBooks().ifPresent(this::println);
    }

    private void expensiveBooks() {
        int value = readInteger("Display all books with a price higher than: ");
        ctrl.filterBooksMoreExpensiveThan(value).forEach(this::println);
    }

    private void cheaperBooks() {
        int value = readInteger("Display all books with a price lower than: ");
        ctrl.filterBooksCheaperThan(value).forEach(this::println);
    }

    private void authorBooks() {
        String author = readString("Give an author to search for: ");
        ctrl.filterBooksByAuthor(author).forEach(this::println);
    }

    private void genreBooks() {
        String genre = readString("Give an genre to search for: ");
        ctrl.filterBooksByGenre(genre).forEach(this::println);
    }

    private void showAvailableBooks() {
        ctrl.availableBooks().forEach(this::println);
    }

    private void showAllBooks() {
        ctrl.getAllBooks().forEach(this::println);
    }

    private void showAllClients() {
        ctrl.getAllClients().forEach(this::println);
    }

    private void menu() {
        println("Options:" +
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
        );
        Integer option = readInteger("Option: ");
        switch (option) {
            case 1:  addClient(); break;
            case 2:  addBook(); break;
            case 3:  deleteClient(); break;
            case 4:  deleteBook(); break;
            case 5:  updateClient(); break;
            case 6:  updateBook(); break;
            case 7:  showAllClients(); break;
            case 8:  showAllBooks(); break;
            case 9:  showAvailableBooks(); break;
            case 10: genreBooks(); break;
            case 11: authorBooks(); break;
            case 12: cheaperBooks(); break;
            case 13: expensiveBooks(); break;
            case 14: mostBooksClient(); break;
            case 15: mostMoneyClient(); break;
            case 16: clientBuyMode(); break;
            case 17: clientReturnMode(); break;
            case 18: clientBooks(); break;
            case 0:  return;
            default: println("Invalid option, try again.");
        }
        menu();
    }

    public void run() {
        menu();
    }
}
