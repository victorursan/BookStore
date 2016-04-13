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
        ControllerService helloService = new ControllerServiceServer(executorService, ctrl);
        TcpServer tcpServer = new TcpServer(executorService, ControllerService.SERVICE_HOST, ControllerService.SERVICE_PORT);

//        tcpServer.addHandler(ControllerService.SAY_HI, (request) -> {
//            CompletableFuture<String> result = helloService.sayHi(request.body());
//            try {
//                return new Message(Message.OK, result.get());
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//            return new Message(Message.ERROR, "");
//        });
//        tcpServer.addHandler(ControllerService.SAY_BYE, (request) -> {
//            CompletableFuture<String> result = helloService.sayBye(request.body());
//            try {
//                return new Message(Message.OK, result.get());
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//            return new Message(Message.ERROR, "");
//        });
        tcpServer.addHandler(ControllerService.GET_ALL_OPTIONS, (request) -> {
            CompletableFuture<String> result = helloService.getAllOptions();
            try {
                return new Message(Message.OK, result.get());
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });
        tcpServer.startServer();
    }
}
