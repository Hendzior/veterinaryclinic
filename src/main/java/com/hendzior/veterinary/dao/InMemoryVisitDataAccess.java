package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class InMemoryVisitDataAccess implements VisitDataAccess {

    private List<Visit> visits = new ArrayList<>();

    @Override
    public void save(Visit visit) {
        visits.add(visit);
    }

    @Override
    public Optional<Visit> findById(Long id) {
        for (Visit visit : visits) {
            if (visit.getId().equals(id)) {
                return Optional.of(visit);
            }
        }
        log.error("Visit id: {} not found!",id);
        return Optional.empty();
    }

    @Override
    public List<Visit> findAll() {
        return visits;
    }

    @Override
    public double incomeFromAllVisits() {
        double sum = 0;

        for (Visit visit : visits) {
            sum += visit.getCost();
        }

        return sum;
    }

    @Override
    public void delete(Visit visit) {
        visits.remove(visit);
    }

    @Override
    public void saveAll(List<Visit> visitsList) {
        visits.addAll(visitsList);
    }

}
