package com.hendzior.veterinary;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Animal {

    protected String name;
    protected String gender;
    protected String type;
    protected int age;
    protected List<Visit> visits = new ArrayList<>();

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
        this.visits = visit;
    }

    List<Visit> getVisit() {
        return visits;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    @Override
    public String toString() {
        return "Animal{" +
            "name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", type='" + type + '\'' +
            ", age=" + (Year.now().getValue() - age) +
            ", visit=" + visits +
            '}';
    }

}

