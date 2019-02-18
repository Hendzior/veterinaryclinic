package com.hendzior.veterinary;

import java.util.ArrayList;
import java.util.List;

public class ConsoleAnimalDataAccess implements AnimalDataAccess {

    CustomerDataAccess customerDataAccess = new ConsoleCustomerDataAccess();

    public Animal addNewAnimal(String name, String gender, String type, String age) {

        switch (type.toLowerCase()) {

            case "dog":

                return new Dog(name, gender, Integer.valueOf(age), type);

            case "cat":
                return new Cat(name, gender, Integer.valueOf(age), type);

            default:
                return new Animal(name, gender, Integer.valueOf(age), type);

        }

    }

    @Override
    public List<Animal> getAnimalsList() {

        List<Animal> animalList = new ArrayList<>();
        for (Customer customer : customerDataAccess.getAllCustomers()) {

            animalList.addAll(customer.getAnimal());

        }
        return animalList;
    }

    @Override
    public void removeAnimal(String id, String animalName) {
        for (Customer customer : customerDataAccess.getAllCustomers()) {

            if (customer.getId().equals(id)){
                for(Animal animal:new ArrayList<>(customer.getAnimal())){
                    if (animal.getName().equals(animalName)){
                        customer.getAnimal().remove(animal);
                    }
                }
            }
        }


    }


}
