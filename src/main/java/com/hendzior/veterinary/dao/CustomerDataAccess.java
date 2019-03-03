package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Customer;

import java.util.List;


public interface CustomerDataAccess {

    void save(Customer customer);

    Customer findById(Long id);

    void delete(Customer customer);

    List<Customer> findAll();

    void saveAll(List<Customer> customers);

}
