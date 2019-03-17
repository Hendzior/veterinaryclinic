package com.hendzior.core.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Dog extends Animal {

    public Dog(String name, String gender, int age, String type, Customer customer) {
        super(name, gender, age, type, customer);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", customer=" + customer +
                '}';
    }
}
