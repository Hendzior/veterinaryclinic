package com.hendzior.veterinary.model;

import com.hendzior.veterinary.model.Animal;

public class Visit {

    private String description;
    private Double cost;
    private Animal animal;

    public Visit() {

    }

    @Override
    public String toString() {
        return "Visit{" +
                "description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Double getCost() {
        return cost;
    }

    public Visit(String description, Double cost) {
        this.description = description;
        this.cost = cost;
    }
}
