package com.BookStore.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by victor on 4/14/16.
 */

@Configuration
public class JdbcConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("victor");
        dataSource.setPassword("papple");
        dataSource.setInitialSize(4);
        dataSource.setMaxActive(5);
        return dataSource;

    }
}
