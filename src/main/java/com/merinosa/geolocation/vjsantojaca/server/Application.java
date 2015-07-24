package com.merinosa.geolocation.vjsantojaca.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EntityScan(basePackages={"com.merinosa.geolocation.vjsantojaca.server.models.entities"})
@EnableJpaRepositories(basePackages = {"com.merinosa.geolocation.vjsantojaca.server.models.repositories"})
@PropertySources({ 
    @PropertySource(value = "classpath:application.properties"), 
}) 
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}