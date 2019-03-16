package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class DatabaseAnimalDataAccess implements AnimalDataAccess {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public Iterable<Animal> findAll() {

        return animalRepository.findAll();
    }

    @Override
    public void delete(Animal animal) {

        animalRepository.delete(animal);

    }

    @Override
    public void saveAll(List<Animal> animalsList) {
        animalRepository.saveAll(animalsList);
    }
}
