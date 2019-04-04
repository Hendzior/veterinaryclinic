package com.hendzior.veterinary.core.dao;

import com.hendzior.veterinary.core.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
