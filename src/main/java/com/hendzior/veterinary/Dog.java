package com.hendzior.veterinary;

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
                ", visit=" + visit +
                '}';
    }
}
