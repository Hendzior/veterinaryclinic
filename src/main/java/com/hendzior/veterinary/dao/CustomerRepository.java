package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
