package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Cat;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public interface AnimalDataAccess {
    Logger log = LoggerFactory.getLogger(AnimalDataAccess.class);

    default Animal createAnimal(String name, String gender, String type, String age, Customer customer) {
        Animal animal;

        switch (type.toLowerCase()) {

            case "dog":

                animal = new Dog(name, gender, Integer.valueOf(age), type, customer);
                break;
            case "cat":
                animal = new Cat(name, gender, Integer.valueOf(age), type, customer);
                break;
            default:
                animal = new Animal(name, gender, Integer.valueOf(age), type, customer);
                break;
        }

        log.info("Animal: {} created successfully", animal);
        return animal;

    }

    Optional<Animal> findById(Long id);

    void save(Animal animal);

    Iterable<Animal> findAll();

    void delete(Animal animal);

    void saveAll(List<Animal> animalsList);

}
