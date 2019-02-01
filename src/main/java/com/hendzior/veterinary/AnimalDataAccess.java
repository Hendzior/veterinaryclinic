package com.hendzior.veterinary;

import java.util.ArrayList;
import java.util.List;

public class AnimalDataAccess {


    public List<Animal> getAnimalsList(List<Customer> customerList) {

        List<Animal> animalList = new ArrayList<>();
        for (Customer customer : customerList) {

            animalList.addAll(customer.getAnimal());

        }
        return animalList;
    }

    public Animal findAnimalByName(String name, List<Customer> customerList) {

        for (Customer customer : customerList) {

            for (Animal animal : customer.getAnimal()) {

                if (animal.getName().equalsIgnoreCase(name)) {
                    return animal;
                }
            }
        }
        return null;
    }


}
