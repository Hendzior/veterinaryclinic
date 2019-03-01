package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;

import java.util.List;

public interface AnimalDataAccess {

    Animal addNewAnimal(String name, String gender, String type, String age);

    Animal findById(Long id);

    List<Animal> getAnimalsList();

    void removeAnimal(Animal animal);

}
