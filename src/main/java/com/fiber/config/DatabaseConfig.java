package com.fiber.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConditionalOnProperty(value = "spring.sql.init.platform", havingValue = "mysql")
    @ConfigurationProperties("mysql.datasource")
    public DataSource mysqlDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.sql.init.platform", havingValue = "postgres")
    @ConfigurationProperties("postgres.datasource")
    public DataSource postgresDataSource() {
        return new DriverManagerDataSource();
    }

}
