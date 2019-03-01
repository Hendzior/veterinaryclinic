package com.hendzior.veterinary.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Animal {

    protected Long id;



    protected String name;
    protected String gender;
    protected String type;
    protected int age;
    protected Customer customer;
    protected List<Visit> visits = new ArrayList<>();

    public Animal(String name, String gender, int age, String type) {
        this.name = name;
        this.gender = gender;
        this.age = (Year.now().getValue() - age);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setVisit(List<Visit> visit) {
        this.visits = visit;
    }

    public List<Visit> getVisit() {
        return visits;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

