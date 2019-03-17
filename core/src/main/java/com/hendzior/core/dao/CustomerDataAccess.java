package com.hendzior.core.dao;

import com.hendzior.core.model.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerDataAccess {

    void save(Customer customer);

    Optional<Customer> findById(Long id);

    void delete(Customer customer);

    Iterable<Customer> findAll();

    void saveAll(List<Customer> customers);

}
