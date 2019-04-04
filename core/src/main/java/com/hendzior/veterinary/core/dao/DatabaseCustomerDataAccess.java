package com.hendzior.veterinary.core.dao;

import com.hendzior.veterinary.core.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DatabaseCustomerDataAccess implements CustomerDataAccess {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Iterable<Customer> findAll() {

        return customerRepository.findAll();
    }

    @Override
    public void saveAll(List<Customer> customers) {

        customerRepository.saveAll(customers);

    }
}
