package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Customer;

import java.util.List;

public interface AnimalDataAccess {

    Animal addNewAnimal(String name, String gender, String type, String age, Customer customer);

    Animal findById(Long id);

    void save(Animal animal);

    List<Animal> findAll();

    void delete(Animal animal);

}
