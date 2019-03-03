package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DatabaseCustomerDataAccess implements CustomerDataAccess {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public void saveAll(List<Customer> customers) {

        customerRepository.saveAll(customers);

    }
}
