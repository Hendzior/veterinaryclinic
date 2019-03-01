package com.hendzior.veterinary;

import com.hendzior.veterinary.dao.CustomerRepository;
import com.hendzior.veterinary.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationRunnerBean implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Menu menu;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Adding sample customers");
        customerRepository.save(new Customer("Name1", "Surname1", " City1"));
        customerRepository.save(new Customer( "Name2", "Surname2", " City2"));
        customerRepository.save(new Customer( "Name3", "Surname3", " City3"));

        menu.menu();
    }
}
