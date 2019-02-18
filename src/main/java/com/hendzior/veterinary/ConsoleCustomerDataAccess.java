package com.hendzior.veterinary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCustomerDataAccess implements CustomerDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleCustomerDataAccess.class);
    private Customers customers = new Customers();

    @Override
    public Customer addNewCustomer(String id, String name, String lName, String city) {
        Customer customer = new Customer(id, name, lName, city);

        return customer;

    }

    @Override
    public void saveNewCustomer(Customer customer) {

        customers.addNewCustomer(customer);

    }

    @Override
    public void saveAnimalToCustomer(Animal animal, String id) {

        for (Customer customer : customers.getCustomersList()) {
            if (customer.getId().equals(id)) {
                customer.addAnimal(animal);
            }
        }

    }

    @Override
    public Customer getCustomer(String id) {
        for (Customer customer : customers.getCustomersList()) {

            if (customer.getId().equals(id)) {
                return customer;
            } else {
                logger.info("Customer not found");
            }

        }
        return null;
    }

    @Override
    public void removeCustomer(String id) {

        for (Customer customer : new ArrayList<>(customers.getCustomersList())) {

            if (customer.getId().equals(id)) {
                customers.getCustomersList().remove(customer);
            }
        }
    }

    @Override
    public List<Customer> getAllCustomers() {

        return customers.getCustomersList();
    }

}
