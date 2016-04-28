package com.BookStore.web.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("com.BookStore.core")
@PropertySources({@PropertySource(value = "classpath:local/db.properties"),
})
public class AppLocalConfig {

    /**
     * Enables placeholders usage with SpEL expressions.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
