package com.hendzior.veterinary;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String id;
    private String name;
    private String lastName;
    private String city;
    private List<Animal> animals = new ArrayList<>();

    public Customer() {

    }

    public Customer(String id, String name, String lastName, String city) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Animal> getAnimal() {
        return animals;

    }

    public void addAnimal(Animal animal) {

        animals.add(animal);
    }

    public Animal findAnimal(String name) {

        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }

    public Customer(String name, String lastName, String city) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;

    }

    @Override
    public String toString() {
        return "Customer{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", city='" + city + '\'' +
            ", animals=" + animals +
            '}';
    }
}
