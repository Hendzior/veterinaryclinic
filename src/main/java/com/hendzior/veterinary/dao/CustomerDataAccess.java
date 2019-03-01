package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Customer;

import java.util.List;


public interface CustomerDataAccess {

    Customer addNewCustomer(String name, String lName, String city);

    void saveNewCustomer(Customer customer);

    void saveAnimalToCustomer(Animal animal, Long id);

    Customer getCustomer(Long id);

    void removeCustomer(Customer customer);

    List<Customer> getAllCustomers();

}
