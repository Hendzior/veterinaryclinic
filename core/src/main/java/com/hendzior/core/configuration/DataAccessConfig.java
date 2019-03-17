package com.hendzior.core.configuration;

import com.hendzior.core.dao.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class DataAccessConfig {

    @Profile("Database")
    @Bean
    public AnimalDataAccess databaseAnimalDataAccess() {

        return new DatabaseAnimalDataAccess();

    }

    @Profile("Database")
    @Bean
    public CustomerDataAccess databaseCustomerDataAccess() {
        return new DatabaseCustomerDataAccess();

    }

    @Profile("Database")
    @Bean
    public VisitDataAccess databaseVisitDataAccess() {
        return new DatabaseVisitDataAccess();
    }

    @Profile("InMemory")
    @Bean
    public AnimalDataAccess animalDataAccess() {

        return new InMemoryAnimalDataAccess();

    }

    @Profile("InMemory")
    @Bean
    public CustomerDataAccess customerDataAccess() {
        return new InMemoryCustomerDataAccess();

    }

    @Profile("InMemory")
    @Bean
    public VisitDataAccess visitDataAccess() {
        return new InMemoryVisitDataAccess();
    }
}
