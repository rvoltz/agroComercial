package com.totvs.agrocomercial.utils;

import org.junit.ClassRule;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
public class PostgresSQLTestsConfiguration {

    @ClassRule
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11-alpine");

    static {
        postgreSQLContainer.withDatabaseName("recomendacao");
        postgreSQLContainer.withUsername("postgres");
        postgreSQLContainer.withPassword("recomendacao");
        postgreSQLContainer.start();
    }

    @Bean
    @Scope("singleton")
    public PostgreSQLContainer postgreSQLContainer() {
        return postgreSQLContainer;
    }

    @Bean
    @Scope("singleton")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(postgreSQLContainer.getJdbcUrl())
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword())
                .driverClassName(postgreSQLContainer.getDriverClassName())
                .build();
    }

    public String getPostgresUser() {
        return postgreSQLContainer.getUsername();
    }

    public String getPostgresPassword() {
        return postgreSQLContainer.getPassword();
    }

    public String getPostgresUrl() {
        return postgreSQLContainer.getJdbcUrl();
    }
}
