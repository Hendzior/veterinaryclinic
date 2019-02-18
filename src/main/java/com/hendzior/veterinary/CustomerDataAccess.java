package com.hendzior.veterinary;

import java.util.List;

public interface CustomerDataAccess {

    Customer addNewCustomer(String id, String name, String lName, String city);

    void saveNewCustomer(Customer customer);

    void saveAnimalToCustomer(Animal animal, String id);

    Customer getCustomer(String id);

    void removeCustomer(String id);

    List<Customer> getAllCustomers();

}
