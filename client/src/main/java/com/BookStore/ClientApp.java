package com.BookStore;

import com.BookStore.core.service.ControllerServiceClient;
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
    }
}
