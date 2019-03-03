package com.hendzior.veterinary.model;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Customer {

    @Transient
    private static final AtomicLong count = new AtomicLong(0);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String city;

    public Customer() {

    }

    public Customer(String name, String lastName, String city) {

        this.id = count.incrementAndGet();
        this.name = name;
        this.lastName = lastName;
        this.city = city;

    }

    public Customer(Long id, String name, String lastName, String city) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.lastName = lastName;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
