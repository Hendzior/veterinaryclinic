package com.hendzior.veterinary.model;

import com.hendzior.veterinary.model.Animal;

import javax.persistence.Entity;

@Entity
public class Cat extends Animal {

    public Cat() {
    }

    public Cat(String name, String gender, int age, String type, Customer customer) {
        super(name, gender, age, type, customer);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", customer=" + customer +
                '}';
    }
}
