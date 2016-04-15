package com.BookStore;

import com.BookStore.service.ControllerServiceClient;
import com.BookStore.ui.Console;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by victor on 4/13/16.
 */
public class ClientApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.BookStore.config");
        ControllerServiceClient controllerServiceClient = context.getBean(ControllerServiceClient.class);
        Console console = new Console(controllerServiceClient);
        console.run();
//        controllerServiceClient.getAllOptions()
//        controllerServiceClient.getAllOptions().handle((message, error) -> {
//            if (error != null) {
//                System.out.println(error.getMessage());
//            }
//            System.out.println(message);
//            return null;
//        });
        //TODO implement ClientUI
//        List<Student> students = studentServiceClient.getStudents();
//        students.stream().forEach(System.out::println);

//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        TcpClient tcpClient = new TcpClient(ControllerService.SERVICE_HOST, ControllerService.SERVICE_PORT);
//        ControllerService helloService = new ControllerServiceClient(executorService, tcpClient);
//        Console console = new Console(helloService);
//        console.run();
//        executorService.shutdownNow();
    }
}
