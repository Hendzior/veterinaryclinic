package com.hendzior.veterinary;

import java.util.ArrayList;
import java.util.List;

public class Customers {

    private static List<Customer> customersList = new ArrayList<>();

    public List<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customer> customersList) {
        Customers.customersList = customersList;
    }

    public void addNewCustomer(Customer customer) {
        customersList.add(customer);
    }

}
