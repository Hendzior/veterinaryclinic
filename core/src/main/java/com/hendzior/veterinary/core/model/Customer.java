package com.hendzior.veterinary.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(min=3, max=20)
    private String name;
    @Size(min=3, max=20)
    private String lastName;
    @Size(min=3, max=20)
    private String city;

    public Customer(String name, String lastName, String city) {

        this.id = count.incrementAndGet();
        this.name = name;
        this.lastName = lastName;
        this.city = city;
    }
}
