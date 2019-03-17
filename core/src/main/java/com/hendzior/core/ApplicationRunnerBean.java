package com.hendzior.core;

import com.hendzior.core.dao.*;
import com.hendzior.core.dao.AnimalDataAccess;
import com.hendzior.core.dao.CustomerDataAccess;
import com.hendzior.core.dao.VisitDataAccess;
import com.hendzior.core.model.Animal;
import com.hendzior.core.model.Customer;
import com.hendzior.core.model.Visit;
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
        visitDataAccess.save(new Visit("visit2", 155.0, animalDataAccess.findById(2L).get()));
        visitDataAccess.save(new Visit("visit3", 25.0, animalDataAccess.findById(3L).get()));
        visitDataAccess.save(new Visit("visit4", 330.0, animalDataAccess.findById(3L).get()));
        log.debug("Visits findAll():{}",visitDataAccess.findAll());

        menu.menu();
    }
}
