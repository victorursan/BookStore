package com.BookStore;

import com.BookStore.Controller.Controller;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Repository.DbRepository.BookDbRepository;
import com.BookStore.Repository.DbRepository.ClientDbRepository;
import com.BookStore.Repository.IRepository;
import com.BookStore.service.ControllerServiceServer;
import com.BookStore.tcp.TcpServer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "victor";
        String pass = "papple";

//        String bookPath = "./server/data/FileData/Books.txt";
//        String clientPath = "./server/data/FileData/Clients.txt";
//        String purchasePath = "./server/data/FileData/Purchase.txt";
        IRepository<Book> bookrepo = new BookDbRepository(url, user, pass, new BookValidator());
        IRepository<Client> clientrepo = new ClientDbRepository(url, user, pass, new ClientValidator(), bookrepo);
        Controller ctrl = new Controller(bookrepo, clientrepo);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ControllerService controllerService = new ControllerServiceServer(executorService, ctrl);
        TcpServer tcpServer = new TcpServer(executorService, ControllerService.SERVICE_HOST, ControllerService.SERVICE_PORT);

        String LINE_SEPARATOR = System.getProperty("line.separator");

        tcpServer.addHandler(ControllerService.GET_ALL_OPTIONS, (request) -> {
            CompletableFuture<String> result = controllerService.getAllOptions();
            try {
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.ADD_CLIENT, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                String firstName = elements[0];
                String lastName = elements[1];
                CompletableFuture<String> result = controllerService.addClient(firstName, lastName);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.ADD_BOOK, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                String title = elements[0];
                String auth = elements[1];
                Long isbn = Long.parseLong(elements[2]);
                String genre = elements[3];
                String publisher = elements[4];
                Integer price = Integer.parseInt(elements[5]);
                CompletableFuture<String> result = controllerService.addBook(title, auth, isbn, genre, publisher, price);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.UPDATE_CLIENT, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                String firstName = elements[1];
                String lastName = elements[2];
                CompletableFuture<String> result = controllerService.updateClient(id, firstName, lastName);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.DELETE_CLIENT, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                CompletableFuture<String> result = controllerService.deleteClient(id);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.UPDATE_BOOK, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                String title = elements[1];
                String auth = elements[2];
                Long isbn = Long.parseLong(elements[3]);
                String genre = elements[4];
                String publisher = elements[5];
                Integer price = Integer.parseInt(elements[6]);
                Boolean available = Boolean.parseBoolean(elements[7]);
                CompletableFuture<String> result = controllerService.updateBook(id, title, auth, isbn, genre, publisher, price, available);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.DELETE_BOOK, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                CompletableFuture<String> result = controllerService.deleteBook(id);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.CLIENT_BOOKS, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                CompletableFuture<String> result = controllerService.clientBooks(id);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.MOST_SPENT, (request) -> {
            try {
                CompletableFuture<String> result = controllerService.clientWhoSpentMost();
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.MOST_BOOKS, (request) -> {
            try {
                CompletableFuture<String> result = controllerService.clientWithMostBooks();
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.ALL_CLIENTS, (request) -> {
            try {
                CompletableFuture<String> result = controllerService.getAllClients();
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.ALL_BOOKS, (request) -> {
            try {
                CompletableFuture<String> result = controllerService.getAllBooks();
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }

        });

        tcpServer.addHandler(ControllerService.AVAILABLE_BOOKS, (request) -> {
            try {
                CompletableFuture<String> result = controllerService.availableBooks();
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }

        });

        tcpServer.addHandler(ControllerService.GENRE_BOOKS, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                String genre = elements[0];
                CompletableFuture<String> result = controllerService.filterBooksByGenre(genre);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.AUTHOR_BOOKS, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                String author = elements[0];
                CompletableFuture<String> result = controllerService.filterBooksByAuthor(author);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.CHEAP_BOOKS, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer price = Integer.parseInt(elements[0]);
                CompletableFuture<String> result = controllerService.filterBooksCheaperThan(price);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.EXPENSIVE_BOOKS, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer price = Integer.parseInt(elements[0]);
                CompletableFuture<String> result = controllerService.filterBooksMoreExpensiveThan(price);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.BUY_BOOK, (request) -> {
            try {
                String[] elements = request.body().split(LINE_SEPARATOR);
                Integer clientId = Integer.parseInt(elements[0]);
                Integer bookId = Integer.parseInt(elements[1]);
                CompletableFuture<String> result = controllerService.buyBook(clientId, bookId);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.addHandler(ControllerService.RETURN_BOOK, (request) -> {
            try {
                String[] elements = request.body().split(LINE_SEPARATOR);
                Integer clientId = Integer.parseInt(elements[0]);
                Integer bookId = Integer.parseInt(elements[1]);
                CompletableFuture<String> result = controllerService.returnBook(clientId, bookId);
                return Message.builder(Message.OK, result.get());
            } catch (Throwable e) {
                return Message.builder(Message.ERROR, "Failure:" + e.getMessage());
            }
        });

        tcpServer.startServer();
    }
}
