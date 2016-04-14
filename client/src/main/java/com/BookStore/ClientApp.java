package com.BookStore;

import com.BookStore.service.ControllerServiceClient;
import com.BookStore.tcp.TcpClient;
import com.BookStore.ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by victor on 4/13/16.
 */
public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(ControllerService.SERVICE_HOST, ControllerService.SERVICE_PORT);
        ControllerService helloService = new ControllerServiceClient(executorService, tcpClient);
        Console console = new Console(helloService);
        console.run();
        executorService.shutdownNow();
    }
}
