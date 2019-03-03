package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Primary
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
    public void save(Customer customer) {

        customers.add(customer);

    }

    @Override
    public Customer findById(Long id) {
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
    public void delete(Customer customer) {

        customers.remove(customer);
    }

    @Override
    public List<Customer> findAll() {

        return customers;
    }

    @Override
    public void saveAll(List<Customer> customersList) {
        customers.addAll(customersList);
    }

}
