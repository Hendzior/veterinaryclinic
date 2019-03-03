package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface VisitDataAccess {

    Visit addNewVisit(String description, double cost);

    void save(Visit visit);

    Visit findById(Long id);

    List<Visit> findAll();

    double incomeFromAllVisits();

    void delete(Visit visit);
}
