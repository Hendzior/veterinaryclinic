package com.hendzior.veterinary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Year;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {

    @Transient
    private static final AtomicLong count = new AtomicLong(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String gender;
    protected String type;
    protected int age;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    public Animal(String name, String gender, int age, String type, Customer customer) {
        this.name = name;
        this.gender = gender;
        this.age = (Year.now().getValue() - age);
        this.type = type;
        this.customer = customer;
        this.id = count.incrementAndGet();
    }
}

