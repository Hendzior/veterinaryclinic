package com.hendzior.veterinary;

import com.hendzior.veterinary.dao.*;
import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Visit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationRunnerBean implements ApplicationRunner {

    @Autowired
    private Environment environment;

    @Autowired
    private VisitDataAccess visitDataAccess;

    @Autowired
    private CustomerDataAccess customerDataAccess;

    @Autowired
    private AnimalDataAccess animalDataAccess;

    @Autowired
    private Menu menu;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.debug("Current App configuration: {}",environment.getActiveProfiles());
        log.debug("Adding sample customers");

        customerDataAccess.save(new Customer("Name1", "Surname1", " City1"));
        customerDataAccess.save(new Customer("Name2", "Surname2", " City2"));
        customerDataAccess.save(new Customer("Name3", "Surname3", " City3"));

        log.debug("Customers findAll():{}",customerDataAccess.findAll());

        Animal animal1 = animalDataAccess.createAnimal("Dog1", "male",  "dog","1990", customerDataAccess.findById(1L).get());
        Animal animal2 = animalDataAccess.createAnimal("cat1", "female",  "cat","1999", customerDataAccess.findById(1L).get());
        Animal animal3 = animalDataAccess.createAnimal("rat1", "female",  "rat","2015", customerDataAccess.findById(3L).get());

        animalDataAccess.save(animal1);
        animalDataAccess.save(animal2);
        animalDataAccess.save(animal3);

        log.debug("Animals findAll():{}",animalDataAccess.findAll());

        visitDataAccess.save(new Visit("visit1", 250.0, animalDataAccess.findById(1L).get()));
        visitDataAccess.save(new Visit("visit1", 155.0, animalDataAccess.findById(2L).get()));
        visitDataAccess.save(new Visit("visit1", 55.0, animalDataAccess.findById(1L).get()));

        log.debug("Visits findAll():{}",visitDataAccess.findAll());

        menu.menu();
    }
}
