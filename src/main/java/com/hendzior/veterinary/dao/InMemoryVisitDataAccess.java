package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class InMemoryVisitDataAccess implements VisitDataAccess {

    private List<Visit> visits = new ArrayList<>();


    @Override
    public Visit addNewVisit(String description, double cost) {

        return null;
    }

    @Override
    public void save(Visit visit) {
        visits.add(visit);
    }

    @Override
    public Visit findById(Long id) {
        for (Visit visit : visits) {
            if (visit.getId().equals(id)) {
                return visit;
            }
        }
        return null;
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

}
