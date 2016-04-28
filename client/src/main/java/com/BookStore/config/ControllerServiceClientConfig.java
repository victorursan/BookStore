package com.BookStore.config;

import com.BookStore.ControllerService;
import com.BookStore.service.ControllerServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class ControllerServiceClientConfig {

    @Bean
    public HessianProxyFactoryBean controllerService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8080/hessian/books");
        factory.setServiceInterface(ControllerService.class);
        return factory;
    }

    @Bean
    public ControllerServiceClient controllerServiceClient() {
        return new ControllerServiceClient();
    }

}


