package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Cat;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class InMemoryAnimalDataAccess implements AnimalDataAccess {

    private List<Animal> animals = new ArrayList<>();

    @Autowired
    private CustomerDataAccess customerDataAccess;

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
        animals.add(animal);
        return animal;

    }

    @Override
    public Animal findById(Long id) {
        for (Animal animal : animals) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public void save(Animal animal) {
        animals.add(animal);
    }

    @Override
    public List<Animal> findAll() {

        return animals;
    }

    @Override
    public void delete(Animal animal) {

        animals.remove(animal);

    }

}
