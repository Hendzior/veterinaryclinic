package com.hendzior.core.dao;

import com.hendzior.core.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class InMemoryCustomerDataAccess implements CustomerDataAccess {

    private List<Customer> customers = new ArrayList<>();

    @Override
    public void save(Customer customer) {

        customers.add(customer);

    }

    @Override
    public Optional<Customer> findById(Long id) {
        for (Customer customer : customers) {

            if (customer.getId().equals(id)) {
                return Optional.of(customer);
            }

        }
        log.error("Customer id:{} not found", id);
        return Optional.empty();
    }

    @Override
    public void delete(Customer customer) {

        customers.remove(customer);
    }

    @Override
    public Iterable<Customer> findAll() {

        return customers;
    }

    @Override
    public void saveAll(List<Customer> customersList) {
        customers.addAll(customersList);
    }

}
