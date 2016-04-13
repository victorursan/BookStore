package com.BookStore;

import com.BookStore.service.ControllerServiceServer;
import com.BookStore.tcp.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {

//        String bookPath = "./server/data/FileData/Books.txt";
//        String clientPath = "./server/data/FileData/Clients.txt";
//        String purchasePath = "./server/data/FileData/Purchase.txt";
//        try {
//            IRepository<Book> bookrepo = new BookFileRepository(new BookValidator(), bookPath);
//            IRepository<Client> clientrepo = new ClientFileRepository(new ClientValidator(), clientPath, purchasePath, bookrepo);
//            Console console = new Console(new Controller(bookrepo, clientrepo));
//            console.run();
//        } catch (BaseException e) {
//            System.out.println(e.getMessage());
//        }
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            ControllerService helloService = new ControllerServiceServer(executorService);
            TcpServer tcpServer = new TcpServer(executorService, ControllerService.SERVICE_HOST, ControllerService.SERVICE_PORT);

            tcpServer.addHandler(ControllerService.SAY_HI, (request) -> {
                Future<String> result = helloService.sayHi(request.body());
                try {
                    return new Message(Message.OK, result.get());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return new Message(Message.ERROR, "");
            });
            tcpServer.addHandler(ControllerService.SAY_BYE, (request) -> {
                Future<String> result = helloService.sayBye(request.body());
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
