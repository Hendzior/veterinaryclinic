package com.hendzior.core.dao;

import com.hendzior.core.model.Visit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface VisitDataAccess {

    void save(Visit visit);

    Optional<Visit> findById(Long id);

    List<Visit> findAll();

    double incomeFromAllVisits();

    void delete(Visit visit);

    void saveAll(List<Visit> visits);
}
