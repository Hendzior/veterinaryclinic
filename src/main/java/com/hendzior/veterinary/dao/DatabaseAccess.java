package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Customer;

import java.util.List;


public interface DatabaseAccess {

    List<Customer> databaseRead();

    void databaseWrite(List<Customer> customersList);

}
