package com.BookStore.web.config;

import com.BookStore.core.ControllerService;
import com.BookStore.core.service.ControllerServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

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


