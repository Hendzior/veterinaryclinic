package com.hendzior.veterinary.core.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Cat extends Animal {

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
