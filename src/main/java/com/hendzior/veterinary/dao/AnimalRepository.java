package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
