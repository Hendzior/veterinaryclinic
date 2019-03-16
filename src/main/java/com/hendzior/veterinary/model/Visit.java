package com.hendzior.veterinary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
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

    public Visit(String description, Double cost, Animal animal) {
        this.description = description;
        this.cost = cost;
        this.animal = animal;
        this.id = count.incrementAndGet();
    }
}
