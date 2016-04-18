package com.BookStore.config;

import com.BookStore.ControllerService;
import com.BookStore.service.ControllerServiceServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;


@Configuration
public class ControllerServiceConfig {

    @Bean
    public ControllerService controllerService() {
        return new ControllerServiceServer();
    }

    @Bean(name="/books")
    HessianServiceExporter exporter() {
        HessianServiceExporter serviceExporter = new HessianServiceExporter();
        serviceExporter.setService(controllerService());
        serviceExporter.setServiceInterface(ControllerService.class);
        return serviceExporter;
    }
}
