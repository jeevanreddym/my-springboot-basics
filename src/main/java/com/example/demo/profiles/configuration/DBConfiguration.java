package com.example.demo.profiles.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    public String devDBConnection() {

        return "DB Connection for Dev - H2";
    }


}
