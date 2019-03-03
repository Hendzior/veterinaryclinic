package com.hendzior.veterinary;

import com.hendzior.veterinary.dao.*;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;


@Component
public class Menu {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private String fileName1 = "customers.json";
    private String fileName2 = "animals.json";
    private String fileName3 = "visits.json";

    private File file1 = new File(fileName1);
    private File file2 = new File(fileName2);
    private File file3 = new File(fileName3);

    private CustomerDataAccess customerDataAccess;
    private DatabaseAccess databaseAccess;
    private VisitDataAccess visitDataAccess;
    private AnimalDataAccess animalDataAccess;
    private DataInputValidator dataInputValidator;

    public Menu(CustomerDataAccess customerDataAccess, DatabaseAccess databaseAccess, VisitDataAccess visitDataAccess, AnimalDataAccess animalDataAccess, DataInputValidator dataInputValidator) {
        this.customerDataAccess = customerDataAccess;
        this.animalDataAccess = animalDataAccess;
        this.databaseAccess = databaseAccess;
        this.visitDataAccess = visitDataAccess;
        this.dataInputValidator = dataInputValidator;
    }

    public void menu() {

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                logger.info("Select option \n 1 = read local database \n 2 = Customers menu \n 3 = Animals menu \n 4 = Visits menu " +
                        "\n 6 = write to local database \n 0 = exit");

                String choice = scanner.nextLine();

                if (choice.equals("1")) {

                    databaseAccess.databaseRead(file1);

                } else if (choice.equals("2")) {

                    customersMenu(scanner);

                } else if (choice.equals("3")) {

                    animalsMenu(scanner);

                } else if (choice.equals("4")) {

                    visitsMenu(scanner);

                } else if (choice.equals("6")) {


                    databaseAccess.databaseWrite(customerDataAccess.findAll(), file1);
                    databaseAccess.databaseWrite(animalDataAccess.findAll(), file2);
                    databaseAccess.databaseWrite(visitDataAccess.findAll(), file3);

                } else if (choice.equals("9")) {
                    return;
                }
            }

        } catch (InputMismatchException e) {
            logger.error("wrong data input", e);

        }
    }

    private void customersMenu(Scanner scanner) {

        logger.info("Select option \n 1 = Add new customer \n 2 = Show all customers " +
                "\n 3 = find a customer \n 4 = remove customer \n or any key = exit");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {

            createCustomer(scanner);

        } else if (choice.equals("2")) {

            logger.info("customers list: " + customerDataAccess.findAll());

        } else if (choice.equals("3")) {

            logger.info("Enter customer id: ");
            Long id = Long.parseLong(scanner.nextLine());

            logger.info("Customer : " + customerDataAccess.findById(id));

        } else if (choice.equals("4")) {

            logger.info("Enter customer id: ");
            Long id = Long.parseLong(scanner.nextLine());
            customerDataAccess.delete(customerDataAccess.findById(id));

        }
    }

    private void createCustomer(Scanner scanner) {
        try {

            logger.info("Enter last name:");
            String lName = scanner.nextLine();
            dataInputValidator.validateLength(lName);

            logger.info("Enter name:");
            String name = scanner.nextLine();
            dataInputValidator.validateLength(name);

            logger.info("Enter city:");
            String city = scanner.nextLine();
            dataInputValidator.validateLength(city);

            customerDataAccess.save(new Customer(name, lName, city));

        } catch (IllegalArgumentException e) {
            logger.info("Wrong data entered");
        }
    }

    private void animalsMenu(Scanner scanner) {

        logger.info("Select option \n 1 = Add new animal \n 2 = Remove animal \n 3 = Show all animals \n 4 = Show animal \n or any key = exit");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {

            createAnimal(scanner);

        } else if (choice.equals("2")) {

            logger.info("Enter animal id:");
            Long id = Long.parseLong(scanner.nextLine());

            animalDataAccess.delete(animalDataAccess.findById(id));

        } else if (choice.equals("3")) {

            logger.info("All animals: " + animalDataAccess.findAll());

        } else if (choice.equals("4")) {

            logger.info("Enter animal id:");
            Long id = Long.parseLong(scanner.nextLine());

            logger.info("Animal: " + animalDataAccess.findById(id));
        }


    }

    private void createAnimal(Scanner scanner) {
        try {
            logger.info("Enter animal name:");
            String aName = scanner.nextLine();
            dataInputValidator.validateLength(aName);
            logger.info("Enter animal gender:");
            String aGender = scanner.nextLine();
            dataInputValidator.validateGen(aGender);

            logger.info("Enter animal type:");
            String aType = scanner.nextLine();

            logger.info("Enter animal year of birth:");
            String aAge = scanner.nextLine();
            dataInputValidator.validateDob(Integer.parseInt(aAge));

            logger.info("enter owners id: ");
            Long id = Long.parseLong(scanner.nextLine());

            animalDataAccess.addNewAnimal(aName, aGender, aType, aAge, customerDataAccess.findById(id));

        } catch (IllegalArgumentException e) {
            logger.info("Wrong data entered");
        }
    }

    private void visitsMenu(Scanner scanner) {

        logger.info("Select option \n 1 = Add new visit \n 2 = Show all visits \n 3 = Show income from all visits \n or any key = exit");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            createVisit(scanner);

        } else if (choice.equals("2")) {

            logger.info("All visits : {} ", visitDataAccess.findAll());

        } else if (choice.equals("3")) {

            logger.info("Income from all visits = {}", visitDataAccess.incomeFromAllVisits());

        } else if (choice.equals("4")) {

            logger.info("Enter visit id:");
            Long id = Long.parseLong(scanner.nextLine());

            visitDataAccess.delete(visitDataAccess.findById(id));
        }

    }

    private void createVisit(Scanner scanner) {
        try {
            logger.info("Enter visit description:");
            String description = scanner.nextLine();

            logger.info("Enter visit cost");
            double cost = Double.parseDouble(scanner.nextLine());

            logger.info("Enter Animal id:");
            Long id = Long.parseLong(scanner.nextLine());

            visitDataAccess.save(new Visit(description, cost, animalDataAccess.findById(id)));

        } catch (NumberFormatException e) {
            logger.info("Enter valid cost");
        }
    }
}