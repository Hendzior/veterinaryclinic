package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class DatabaseVisitDataAccess implements VisitDataAccess {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public void save(Visit visit) {
        visitRepository.save(visit);
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public List<Visit> findAll() {
        return ((List<Visit>) visitRepository.findAll());
    }

    @Override
    public double incomeFromAllVisits() {

        double sum = 0;

        for (Visit visit : visitRepository.findAll()) {
            sum += visit.getCost();
        }
        return sum;
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void saveAll(List<Visit> visits) {
        visitRepository.saveAll(visits);
    }
}
