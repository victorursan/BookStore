package com.BookStore.config;

import com.BookStore.ControllerService;
import com.BookStore.service.ControllerServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Created by victor on 4/14/16.
 */

@Configuration
public class ControllerServiceClientConfig {

    @Bean
    public RmiProxyFactoryBean controllerService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost:1099/ControllerService");
        rmiProxy.setServiceInterface(ControllerService.class);
        return rmiProxy;
    }

    @Bean
    public ControllerServiceClient controllerServiceClient() {
        return new ControllerServiceClient();
    }


}

