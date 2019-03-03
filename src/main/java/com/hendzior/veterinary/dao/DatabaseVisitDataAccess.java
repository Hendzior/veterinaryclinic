package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseVisitDataAccess implements VisitDataAccess {


    private VisitRepository visitRepository;

    public DatabaseVisitDataAccess(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit addNewVisit(String description, double cost) {
        return null;
    }

    @Override
    public void save(Visit visit) {
        visitRepository.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).get();
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
}
