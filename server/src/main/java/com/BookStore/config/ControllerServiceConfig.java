package com.BookStore.config;

import com.BookStore.ControllerService;
import com.BookStore.service.ControllerServiceServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by victor on 4/14/16.
 */
@Configuration
public class ControllerServiceConfig {

    @Bean
    public ControllerService controllerService() {
        return new ControllerServiceServer();
    }

    @Bean
    public RmiServiceExporter rmiService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("ControllerService");
        rmiService.setServiceInterface(ControllerService.class);
        rmiService.setService(controllerService());
        return rmiService;
    }

}
