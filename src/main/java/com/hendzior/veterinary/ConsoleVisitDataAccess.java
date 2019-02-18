package com.hendzior.veterinary;

import java.util.ArrayList;
import java.util.List;

public class ConsoleVisitDataAccess implements VisitDataAccess {

    CustomerDataAccess customerDataAccess = new ConsoleCustomerDataAccess();

    @Override
    public Visit addNewVisit(String description, double cost) {

        Visit visit = new Visit(description, cost);
        return visit;

    }

    @Override
    public List<Visit> getAllVisits() {
        List<Visit> visits = new ArrayList<>();
        for (Customer customer : customerDataAccess.getAllCustomers()) {
            for (Animal animal : customer.getAnimal()) {
                visits.addAll(animal.getVisit());
            }

        }
        return visits;
    }

    @Override
    public double incomeFromAllVisits() {
        double sum = 0;

        for (Visit visit : getAllVisits()) {
            sum += visit.getCost();
        }

        return sum;
    }

    public void saveVisit(Visit visit, String id, String animalName) {

        for (Customer customer : customerDataAccess.getAllCustomers()) {
            if (customer.getId().equals(id)) {
                for (Animal animal : customer.getAnimal()) {
                    if (animal.getName().equals(animalName)) {
                        animal.addVisit(visit);
                    }
                }
            }
        }

    }

}
