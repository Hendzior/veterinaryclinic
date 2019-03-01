package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryCustomerDataAccess implements CustomerDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryCustomerDataAccess.class);

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    private List<Customer> customers = new ArrayList<>();

    @Override
    public Customer addNewCustomer(String name, String lName, String city) {

        Customer customer = new Customer(name, lName, city);

        return customer;

    }

    @Override
    public void saveNewCustomer(Customer customer) {

        customers.add(customer);

    }

    @Override
    public void saveAnimalToCustomer(Animal animal, Long id) {

        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                customer.addAnimal(animal);
            }
        }

    }

    @Override
    public Customer getCustomer(Long id) {
        for (Customer customer : customers) {

            if (customer.getId().equals(id)) {
                return customer;
            } else {
                logger.info("Customer not found");
            }

        }
        return null;
    }

    @Override
    public void removeCustomer(Customer customer) {

        customers.remove(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {

        return customers;
    }

}
