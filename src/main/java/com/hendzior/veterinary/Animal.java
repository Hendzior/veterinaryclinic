package com.hendzior.veterinary;

import java.time.Year;
import java.util.List;

public class Animal {

    String name;
    String gender;
    String type;
    int age;
    List<Visit> visit;

    Animal(String name, String gender, int age, String type) {
        this.name = name;
        this.gender = gender;
        this.age = (Year.now().getValue() - age);
        this.type = type;
    }

    String getName() {
        return name;
    }
    void setVisit(List<Visit> visit) {
        this.visit = visit;
    }

    List<Visit> getVisit() {
        return visit;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", age=" + (Year.now().getValue() - age) +
                ", visit=" + visit +
                '}';
    }




}

