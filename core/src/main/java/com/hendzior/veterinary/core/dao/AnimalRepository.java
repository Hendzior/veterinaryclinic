package com.hendzior.veterinary.core.dao;

import com.hendzior.veterinary.core.model.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
