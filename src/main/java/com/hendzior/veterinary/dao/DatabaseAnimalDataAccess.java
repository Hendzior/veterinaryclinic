package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Cat;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DatabaseAnimalDataAccess implements AnimalDataAccess {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Animal addNewAnimal(String name, String gender, String type, String age, Customer customer) {

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

        animalRepository.save(animal);

        return animal;
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id).get();
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public List<Animal> findAll() {

        List<Animal> animals = new ArrayList<>();
        for (Animal animal : animalRepository.findAll()) {
            animals.add(animal);
        }
        return animals;
    }

    @Override
    public void delete(Animal animal) {

        animalRepository.delete(animal);

    }
}
