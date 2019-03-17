package com.hendzior.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories(basePackages = "com.hendzior")
@EntityScan(basePackages = "com.hendzior")
@ComponentScan(basePackages = "com.hendzior")
@SpringBootApplication
public class VeterinaryclinicWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinaryclinicWebApplication.class, args);
    }

}
