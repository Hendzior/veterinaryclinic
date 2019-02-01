package com.hendzior.veterinary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VisitDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(VisitDataAccess.class);

    public void addVisit(List<Customer> customerList, String custName, String animalName) {

        for (Customer customer : customerList) {

            if (customer.getLastName().equalsIgnoreCase(custName)) {

                for (Animal animal : customer.getAnimal()) {
                    System.out.println(animal);
                    if (animal.getName().equalsIgnoreCase(animalName)) {

                        try (Scanner scanner = new Scanner(System.in)) {

                            logger.info("Enter visit description:");
                            String description = scanner.nextLine();

                            logger.info("Enter visit cost");
                            double cost = scanner.nextDouble();

                            Visit visit = new Visit(description, cost);

                            if (animal.getVisit() == null) {
                                List<Visit> visitList = new ArrayList<>();
                                animal.setVisit(visitList);

                            }

                            animal.getVisit().add(visit);

                        } catch (InputMismatchException e) {

                        }
                        return;

                    }

                }
                logger.info("Animal: " + animalName + " not found");
                return;
            }
        }
        logger.info("Customer: " + custName + " not found");

    }

    public List<Visit> getVisitList(List<Customer> customerList) {

        List<Visit> visitList = new ArrayList<>();
        for (Customer customer : customerList) {

            for (Animal animal : customer.getAnimal()) {
                if (animal.getVisit() != null) {

                    visitList.addAll(animal.getVisit());
                }
            }
        }
        return visitList;
    }

    public double incomeFromAllVisits(List<Visit> visitList) {

        double income = 0;
        for (Visit visit : visitList) {

            income += visit.getCost();
        }
        return income;
    }

}
