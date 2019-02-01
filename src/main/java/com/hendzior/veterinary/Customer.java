package com.hendzior.veterinary;

import java.util.List;

public class Customer {

    private String name;
    private String lastName;
    private String city;
    private List<Animal> animal;

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "com.hendzior.veterinary.Customer{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", animal=" + animal +
                '}';
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public Customer(String name, String lastName, String city, List<Animal> animal) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.animal = animal;
    }


}
