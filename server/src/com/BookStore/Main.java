package com.BookStore;

import com.BookStore.Controller.Controller;
import com.BookStore.Model.Book;
import com.BookStore.Model.Client;
import com.BookStore.Model.Validators.BookValidator;
import com.BookStore.Model.Validators.ClientValidator;
import com.BookStore.Repository.FileRepository.BookFileRepository;
import com.BookStore.Repository.FileRepository.ClientFileRepository;
import com.BookStore.Repository.IRepository;
import com.BookStore.service.ControllerServiceServer;
import com.BookStore.tcp.TcpServer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        String bookPath = "./server/data/FileData/Books.txt";
        String clientPath = "./server/data/FileData/Clients.txt";
        String purchasePath = "./server/data/FileData/Purchase.txt";
        IRepository<Book> bookrepo = new BookFileRepository(new BookValidator(), bookPath);
        IRepository<Client> clientrepo = new ClientFileRepository(new ClientValidator(), clientPath, purchasePath, bookrepo);
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
                e.printStackTrace();
            }
            return Message.builder(Message.ERROR, "");
        });

        tcpServer.addHandler(ControllerService.ADD_CLIENT, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                String firstName = elements[0];
                String lastName = elements[1];
                CompletableFuture result = controllerService.addClient(firstName, lastName);
                result.get();
                return Message.builder(Message.OK, "Success");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return Message.builder(Message.ERROR, "Failure");
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
                CompletableFuture result = controllerService.addBook(title, auth, isbn, genre, publisher, price);
                result.get();
                return Message.builder(Message.OK, "Success");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return Message.builder(Message.ERROR, "Failure");
        });

        tcpServer.addHandler(ControllerService.UPDATE_CLIENT, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                String firstName = elements[1];
                String lastName = elements[2];
                CompletableFuture result = controllerService.updateClient(id, firstName, lastName);
                result.get();
                return Message.builder(Message.OK, "Success");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return Message.builder(Message.ERROR, "Failure");
        });

        tcpServer.addHandler(ControllerService.DELETE_CLIENT, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                CompletableFuture result = controllerService.deleteClient(id);
                result.get();
                return Message.builder(Message.OK, "Success");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return Message.builder(Message.ERROR, "Failure");
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
                CompletableFuture result = controllerService.updateBook(id, title, auth, isbn, genre, publisher, price);
                result.get();
                return Message.builder(Message.OK, "Success");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return Message.builder(Message.ERROR, "Failure");
        });

        tcpServer.addHandler(ControllerService.DELETE_BOOK, (request) -> {
            String[] elements = request.body().split(LINE_SEPARATOR);
            try {
                Integer id = Integer.parseInt(elements[0]);
                CompletableFuture result = controllerService.deleteBook(id);
                result.get();
                return Message.builder(Message.OK, "Success");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return Message.builder(Message.ERROR, "Failure");
        });

        tcpServer.startServer();
    }
}
