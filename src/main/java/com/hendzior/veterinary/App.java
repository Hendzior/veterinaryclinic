package com.hendzior.veterinary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("Starting app");

        String fileName = "file.json";
        File file = new File(fileName);

        CustomerDataAccess customerDataAccess = new CustomerDataAccess(file);
        List<Customer> customerList = customerDataAccess.jsonFileReader();

        VisitDataAccess visitDataAccess = new VisitDataAccess();
        try (Scanner scanner = new Scanner(System.in)) {
            logger.info("Add new customer? yes/no");

            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                Customer customer = customerDataAccess.addNewCustomer();

                if (customer != null) {
                    customerList.add(customer);
                }
            }

            logger.info("Add new visit? yes/no");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                logger.info("Enter customer name: ");
                String name = scanner.nextLine();
                logger.info("Enter animal name");
                String animalName = scanner.nextLine();

                visitDataAccess.addVisit(customerList, name, animalName);
            }

        } catch (InputMismatchException e){
            logger.error("wrong data input", e);
        }

        AnimalDataAccess animalDataAccess = new AnimalDataAccess();
        List<Animal> animalList = animalDataAccess.getAnimalsList(customerList);

        double income = visitDataAccess.incomeFromAllVisits(visitDataAccess.getVisitList(customerList));

        logger.info("List of all Customers: {} ", customerList);
        logger.info("List of all animals: {}", animalList);
        logger.info("Total income = {}", income);

        customerDataAccess.jsonFileWriter(customerList);

    }

}
