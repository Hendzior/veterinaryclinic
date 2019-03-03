package com.hendzior.veterinary.model;

import com.hendzior.veterinary.model.Animal;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Visit {

    @Transient
    private static final AtomicLong count = new AtomicLong(0);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double cost;
    @ManyToOne
    @JoinColumn(name = "ANIMAL_ID")
    private Animal animal;

    public Visit() {

    }

    public Visit(String description, Double cost, Animal animal) {
        this.description = description;
        this.cost = cost;
        this.animal = animal;
        this.id = count.incrementAndGet();
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", animal=" + animal +
                '}';
    }

    public Double getCost() {
        return cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
