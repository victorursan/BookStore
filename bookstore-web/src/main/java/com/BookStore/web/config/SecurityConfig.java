package com.BookStore.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


/**
 * Created by radu.
 */

@Configuration
@ImportResource({"classpath:webSecurityConfig.xml"})
@ComponentScan("com.BookStore.web.security")
public class SecurityConfig {

    public SecurityConfig() {
        super();
    }

}

