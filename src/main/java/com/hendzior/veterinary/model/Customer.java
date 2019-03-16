package com.hendzior.veterinary.model;

import lombok.*;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Customer(String name, String lastName, String city) {

        this.id = count.incrementAndGet();
        this.name = name;
        this.lastName = lastName;
        this.city = city;
    }
}
