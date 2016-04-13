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
        tcpServer.startServer();
    }
}
