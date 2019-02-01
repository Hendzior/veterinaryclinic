package com.hendzior.veterinary;

public class Cat extends Animal {


    public Cat(String name, String gender, int age, String type) {
        super(name, gender, age, type);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", visit=" + visit +
                '}';
    }
}