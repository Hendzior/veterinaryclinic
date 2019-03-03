package com.hendzior.veterinary.model;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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


    public Animal() {
    }

     public Animal(String name, String gender, int age, String type, Customer customer) {
        this.name = name;
        this.gender = gender;
        this.age = (Year.now().getValue() - age);
        this.type = type;
        this.customer = customer;
        this.id = count.incrementAndGet();
    }

    public String getName() {
        return name;
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
                "id='" +id+ '\''+
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", age=" + (Year.now().getValue() - age) +
                ". owner=" + customer+
                 '}';
    }


}

