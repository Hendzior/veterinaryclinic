package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface VisitDataAccess {

    Visit addNewVisit(String description, double cost);

    List<Visit> getAllVisits();

    double incomeFromAllVisits();

    void saveVisit(Visit visit, Long id, String animalName);
}
