package com.hendzior.veterinary;

import java.util.List;

public interface AnimalDataAccess {

    Animal addNewAnimal(String name, String gender, String type, String age);

    List<Animal> getAnimalsList();

    void removeAnimal(String id, String animalName);

}
