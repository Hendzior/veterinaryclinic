package com.hendzior.veterinary;

import java.util.List;

public interface VisitDataAccess {

    Visit addNewVisit(String description, double cost);

    List<Visit> getAllVisits();

    double incomeFromAllVisits();

}
