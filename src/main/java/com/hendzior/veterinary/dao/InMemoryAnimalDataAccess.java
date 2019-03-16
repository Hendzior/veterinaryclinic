package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class InMemoryAnimalDataAccess implements AnimalDataAccess {

    private List<Animal> animals = new ArrayList<>();

    @Override
    public Optional<Animal> findById(Long id) {
        for (Animal animal : animals) {
            if (animal.getId().equals(id)) {
                return Optional.of(animal);
            }
        }
        log.error("Animal with id:{} not found", id);
        return Optional.empty();
    }

    @Override
    public void save(Animal animal) {
        animals.add(animal);
    }

    @Override
    public List<Animal> findAll() {

        return animals;
    }

    @Override
    public void delete(Animal animal) {

        animals.remove(animal);

    }

    @Override
    public void saveAll(List<Animal> animalsList) {
        animals.addAll(animalsList);
    }

}
