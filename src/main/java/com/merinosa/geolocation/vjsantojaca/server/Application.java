package com.merinosa.geolocation.vjsantojaca.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.merinosa.geolocation.vjsantojaca.server.models.repositories"})
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}