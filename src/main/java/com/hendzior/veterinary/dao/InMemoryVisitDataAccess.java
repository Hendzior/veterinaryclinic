package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryVisitDataAccess implements VisitDataAccess {

    CustomerDataAccess customerDataAccess = new InMemoryCustomerDataAccess();

    @Override
    public Visit addNewVisit(String description, double cost) {

        Visit visit = new Visit(description, cost);
        return visit;

    }

    @Override
    public List<Visit> getAllVisits() {
        List<Visit> visits = new ArrayList<>();
        for (Customer customer : customerDataAccess.getAllCustomers()) {
            for (Animal animal : customer.getAnimals()) {
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

    public void saveVisit(Visit visit, Long id, String animalName) {

        for (Customer customer : customerDataAccess.getAllCustomers()) {
            if (customer.getId().equals(id)) {
                for (Animal animal : customer.getAnimals()) {
                    if (animal.getName().equals(animalName)) {
                        animal.addVisit(visit);
                    }
                }
            }
        }

    }

}
