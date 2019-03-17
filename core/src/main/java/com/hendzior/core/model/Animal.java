package com.hendzior.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min=3, max=20)
    protected String name;
    @NotEmpty
    protected String gender;
    protected String type;
    @NotNull
    protected int age;
    @NotFound(action = NotFoundAction.IGNORE)
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

