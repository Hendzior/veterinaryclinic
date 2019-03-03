package com.hendzior.veterinary;

import com.hendzior.veterinary.dao.*;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Visit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationRunnerBean implements ApplicationRunner {


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

        log.info("Adding sample customers");

        customerDataAccess.save(new Customer("Name1", "Surname1", " City1"));
        customerDataAccess.save(new Customer("Name2", "Surname2", " City2"));
        customerDataAccess.save(new Customer("Name3", "Surname3", " City3"));

        animalDataAccess.addNewAnimal("Dog1", "male",  "dog","1990", customerDataAccess.findById(1L));
        animalDataAccess.addNewAnimal("cat1", "female",  "cat","1999", customerDataAccess.findById(1L));
        animalDataAccess.addNewAnimal("rat1", "female",  "rat","2015", customerDataAccess.findById(3L));

        visitDataAccess.save(new Visit("visit1", 250.0, animalDataAccess.findById(1L)));
        visitDataAccess.save(new Visit("visit1", 155.0, animalDataAccess.findById(2L)));
        visitDataAccess.save(new Visit("visit1", 55.0, animalDataAccess.findById(1L)));

        menu.menu();
    }
}
