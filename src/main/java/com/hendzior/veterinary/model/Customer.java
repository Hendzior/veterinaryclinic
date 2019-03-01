package com.hendzior.veterinary.model;

import com.hendzior.veterinary.model.Animal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String city;

    @Transient
    private List<Animal> animals = new ArrayList<>();

    public Customer() {

    }

    public Customer(String name, String lastName, String city) {

        this.name = name;
        this.lastName = lastName;
        this.city = city;
    }

    public Customer(Long id, String name, String lastName, String city) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Animal> getAnimals() {
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
