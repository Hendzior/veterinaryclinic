package com.hendzior.veterinary.controller;

import com.hendzior.veterinary.dao.CustomerRepository;
import com.hendzior.veterinary.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public Iterable<Customer> getCustomers() {

             return customerRepository.findAll();

    }

}
