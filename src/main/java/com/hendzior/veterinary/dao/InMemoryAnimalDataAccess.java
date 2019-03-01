package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.dao.AnimalDataAccess;
import com.hendzior.veterinary.dao.CustomerDataAccess;
import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Cat;
import com.hendzior.veterinary.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryAnimalDataAccess implements AnimalDataAccess {

    private List<Animal> animals = new ArrayList<>();

    @Autowired
    private CustomerDataAccess customerDataAccess;

    public Animal addNewAnimal(String name, String gender, String type, String age) {

        Animal animal;

        switch (type.toLowerCase()) {

            case "dog":

                animal = new Dog(name, gender, Integer.valueOf(age), type);

            case "cat":
                animal = new Cat(name, gender, Integer.valueOf(age), type);

            default:
                animal = new Animal(name, gender, Integer.valueOf(age), type);

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
    public List<Animal> getAnimalsList() {

        return animals;
    }

    @Override
    public void removeAnimal(Animal animal) {

        animals.remove(animal);

    }

}
