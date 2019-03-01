package com.hendzior.veterinary.model;

import com.hendzior.veterinary.model.Animal;

public class Dog extends Animal {

    public Dog(String name, String gender, int age, String type) {
        super(name, gender, age, type);

    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", visits=" + visits +
                '}';
    }
}
