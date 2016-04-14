package com.BookStore.service;

import com.BookStore.Controller.Controller;
import com.BookStore.ControllerService;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

/**
 * Created by victor on 4/13/16.
 */
public class ControllerServiceServer implements ControllerService {
    private ExecutorService executorService;
    private Controller ctrl;
    private String LINE_SEPARATOR = System.getProperty("line.separator");

    public ControllerServiceServer(ExecutorService executorService, Controller ctrl) {
        this.executorService = executorService;
        this.ctrl = ctrl;
    }

    private <T> CompletableFuture<T> sendRequestWithMessage(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, executorService);
    }

    @Override
    public CompletableFuture<String> getAllOptions() {
        return sendRequestWithMessage(() -> "Options:" +
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
                "\n0. Exit");
    }

    @Override
    public CompletableFuture<String> addClient(String firstName, String lastName) {
        return sendRequestWithMessage(() -> {
            ctrl.addClient(firstName, lastName);
            return "Success";
        });
    }

    @Override
    public CompletableFuture<String> addBook(String title, String auth, Long isbn, String genre, String publisher, Integer price) {
        return sendRequestWithMessage(() -> {
            ctrl.addBook(title, auth, isbn, genre, publisher, price);
            return "Success";
        });
    }

    @Override
    public CompletableFuture<String> updateClient(Integer id, String firstName, String lastName) {
        return sendRequestWithMessage(() -> {
            ctrl.updateClient(id, firstName, lastName);
            return "Success";
        });
    }

    @Override
    public CompletableFuture<String> deleteClient(Integer id) {
        return sendRequestWithMessage(() -> {
            ctrl.deleteClient(id);
            return "Success";
        });
    }

    @Override
    public CompletableFuture<String> updateBook(Integer id, String title, String auth, Long isbn, String genre, String publisher, Integer price, Boolean available) {
        return sendRequestWithMessage(() -> {
            ctrl.updateBook(id, title, auth, isbn, genre, publisher, price, available);
            return "Success";
        });
    }

    @Override
    public CompletableFuture<String> deleteBook(Integer id) {
        return sendRequestWithMessage(() -> {
            ctrl.deleteBook(id);
            return "Success";
        });
    }

    @Override
    public CompletableFuture<String> clientBooks(Integer id) {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Optional<List<Book>> optBooks = ctrl.clientBooks(id);
            if (optBooks.isPresent()) {
                for (Book book : optBooks.get()) {
                    toReturn += String.format("%d, %s, %s, %s, %s, %s, %s" + LINE_SEPARATOR, book.getId(), book.getTitle(), book.getAuthor(),
                            book.getISBN().toString(), book.getGenre(), book.getPublisher(), book.getPrice().toString());
                }
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> clientWhoSpentMost() {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Optional<Client> clientOpt = ctrl.clientWhoSpentMost();
            if (clientOpt.isPresent()) {
                Client client = clientOpt.get();
                toReturn = String.format("%d, %s, %s" + LINE_SEPARATOR, client.getId(), client.getFirstName(),
                        client.getLastName());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> clientWithMostBooks() {
        return sendRequestWithMessage(() -> {
            String toRet = "";
            Optional<Client> clientOpt = ctrl.clientWithMostBooks();
            if (clientOpt.isPresent()) {
                Client client = clientOpt.get();
                toRet = String.format("%d, %s, %s" + LINE_SEPARATOR, client.getId(), client.getFirstName(),
                        client.getLastName());
            }
            return toRet;
        });
    }

    @Override
    public CompletableFuture<String> getAllClients() {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Iterable<Client> optClient = ctrl.getAllClients();
            for (Client client : optClient) {
                toReturn += String.format("%d, %s, %s" + LINE_SEPARATOR, client.getId(), client.getFirstName(),
                        client.getLastName());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> getAllBooks() {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Iterable<Book> optBooks = ctrl.getAllBooks();
            for (Book book : optBooks) {
                toReturn += String.format("%d, %s, %s, %s, %s, %s, %s" + LINE_SEPARATOR, book.getId(), book.getTitle(), book.getAuthor(),
                        book.getISBN().toString(), book.getGenre(), book.getPublisher(), book.getPrice().toString());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> availableBooks() {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Iterable<Book> optBooks = ctrl.availableBooks();
            for (Book book : optBooks) {
                toReturn += String.format("%d, %s, %s, %s, %s, %s, %s" + LINE_SEPARATOR, book.getId(), book.getTitle(), book.getAuthor(),
                        book.getISBN().toString(), book.getGenre(), book.getPublisher(), book.getPrice().toString());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> filterBooksByGenre(String genre) {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Iterable<Book> optBooks = ctrl.filterBooksByGenre(genre);
            for (Book book : optBooks) {
                toReturn += String.format("%d, %s, %s, %s, %s, %s, %s" + LINE_SEPARATOR, book.getId(), book.getTitle(), book.getAuthor(),
                        book.getISBN().toString(), book.getGenre(), book.getPublisher(), book.getPrice().toString());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> filterBooksByAuthor(String auth) {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Iterable<Book> optBooks = ctrl.filterBooksByAuthor(auth);
            for (Book book : optBooks) {
                toReturn += String.format("%d, %s, %s, %s, %s, %s, %s" + LINE_SEPARATOR, book.getId(), book.getTitle(), book.getAuthor(),
                        book.getISBN().toString(), book.getGenre(), book.getPublisher(), book.getPrice().toString());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> filterBooksCheaperThan(Integer price) {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Iterable<Book> optBooks = ctrl.filterBooksCheaperThan(price);
            for (Book book : optBooks) {
                toReturn += String.format("%d, %s, %s, %s, %s, %s, %s" + LINE_SEPARATOR, book.getId(), book.getTitle(), book.getAuthor(),
                        book.getISBN().toString(), book.getGenre(), book.getPublisher(), book.getPrice().toString());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> filterBooksMoreExpensiveThan(Integer price) {
        return sendRequestWithMessage(() -> {
            String toReturn = "";
            Iterable<Book> optBooks = ctrl.filterBooksMoreExpensiveThan(price);
            for (Book book : optBooks) {
                toReturn += String.format("%d, %s, %s, %s, %s, %s, %s" + LINE_SEPARATOR, book.getId(), book.getTitle(), book.getAuthor(),
                        book.getISBN().toString(), book.getGenre(), book.getPublisher(), book.getPrice().toString());
            }
            return toReturn;
        });
    }

    @Override
    public CompletableFuture<String> returnBook(Integer clientId, Integer bookId) {
        return sendRequestWithMessage(() -> {
            ctrl.returnBook(clientId, bookId);
            return "Success";
        });
    }

    @Override
    public CompletableFuture<String> buyBook(Integer clientId, Integer bookId) {
        return sendRequestWithMessage(() -> {
            ctrl.buyBook(clientId, bookId);
            return "Success";
        });
    }


}