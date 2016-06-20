package com.BookStore.core;

import com.BookStore.ControllerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Created by victor on 6/20/16.
 */
@Configuration
public class HessianConfig {

    @Bean
    public HessianProxyFactoryBean hessianControllerService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8081/hessian/books");
        factory.setServiceInterface(ControllerService.class);
        return factory;
    }
}
