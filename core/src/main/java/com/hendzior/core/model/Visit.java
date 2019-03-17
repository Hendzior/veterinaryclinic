package com.hendzior.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min=3)
    private String description;
    @NotNull
    @Min(0)
    private Double cost;
    @NotFound(action = NotFoundAction.IGNORE)
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
