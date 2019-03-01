package com.hendzior.veterinary;

import com.hendzior.veterinary.dao.*;
import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;


@Component
public class Menu {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private String fileName = "file.json";
    private File file = new File(fileName);
    private CustomerDataAccess customerDataAccess;
    private DatabaseAccess databaseAccess;
    private VisitDataAccess visitDataAccess;
    private AnimalDataAccess animalDataAccess;
    private DataInputValidator dataInputValidator;

    public Menu(CustomerDataAccess customerDataAccess, DatabaseAccess databaseAccess, VisitDataAccess visitDataAccess, AnimalDataAccess animalDataAccess, DataInputValidator dataInputValidator){
        this.customerDataAccess=customerDataAccess;
        this.animalDataAccess=animalDataAccess;
        this.databaseAccess=databaseAccess;
        this.visitDataAccess=visitDataAccess;
        this.dataInputValidator=dataInputValidator;
    }

//    @Autowired
//    private CustomerDataAccess customerDataAccess;


//    private JsonDatabaseAccess databaseAccess = new JsonDatabaseAccess(file);
//    private InMemoryVisitDataAccess visitDataAccess = new InMemoryVisitDataAccess();
//    private DataInputValidator dataInputValidator = new DataInputValidator();
//    private AnimalDataAccess animalDataAccess = new InMemoryAnimalDataAccess();


    public void menu() {

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                logger.info("Select option \n 1 = read local database \n 2 = Customers menu \n 3 = Animals menu \n 4 = Visits menu " +
                        "\n 6 = write to local database \n 0 = exit");

                String choice = scanner.nextLine();

                if (choice.equals("1")) {

                    customerDataAccess.getAllCustomers().addAll(databaseAccess.databaseRead());

                } else if (choice.equals("2")) {

                    customersMenu(scanner);

                } else if (choice.equals("3")) {

                    animalsMenu(scanner);

                } else if (choice.equals("4")) {

                    visitsMenu(scanner);

                } else if (choice.equals("6")) {

                    databaseAccess.databaseWrite(customerDataAccess.getAllCustomers());

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

            logger.info("customers list: " + customerDataAccess.getAllCustomers());

        } else if (choice.equals("3")) {

            logger.info("Enter customer id: ");
            Long id = Long.parseLong(scanner.nextLine());

            logger.info("Customer : " + customerDataAccess.getCustomer(id));

        } else if (choice.equals("4")) {

            logger.info("Enter customer id: ");
            Long id = Long.parseLong(scanner.nextLine());
            customerDataAccess.removeCustomer(customerDataAccess.getCustomer(id));

        }
    }

    private void createCustomer(Scanner scanner) {
        try {
//            logger.info("Enter id:");
//            Long id = Long.parseLong(scanner.nextLine());

            logger.info("Enter last name:");
            String lName = scanner.nextLine();
            dataInputValidator.validateLength(lName);

            logger.info("Enter name:");
            String name = scanner.nextLine();
            dataInputValidator.validateLength(name);

            logger.info("Enter city:");
            String city = scanner.nextLine();
            dataInputValidator.validateLength(city);

            Customer customer = customerDataAccess.addNewCustomer(name, lName, city);

            customerDataAccess.saveNewCustomer(customer);
        } catch (IllegalArgumentException e) {
            logger.info("Wrong data entered");
        }
    }

    private void animalsMenu(Scanner scanner) {

        logger.info("Select option \n 1 = Add new animal \n 2 = Remove animal \n or any key = exit");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {

            createAnimal(scanner);

        } else if (choice.equals(2)) {

            logger.info("Enter animal id:");
            Long id = Long.parseLong(scanner.nextLine());

            animalDataAccess.removeAnimal(animalDataAccess.findById(id));

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

            Animal animal = animalDataAccess.addNewAnimal(aName, aGender, aType, aAge);

            logger.info("enter owners id: ");
            Long id = Long.parseLong(scanner.nextLine());

            customerDataAccess.saveAnimalToCustomer(animal, id);

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

            logger.info("All visits : {} ", visitDataAccess.getAllVisits());

        } else if (choice.equals("3")) {

            logger.info("Income from all visits = {}", visitDataAccess.incomeFromAllVisits());

        }

    }

    private void createVisit(Scanner scanner) {
        try {
            logger.info("Enter visit description:");
            String description = scanner.nextLine();

            logger.info("Enter visit cost");
            double cost = Double.parseDouble(scanner.nextLine());

            Visit visit = visitDataAccess.addNewVisit(description, cost);

            logger.info("Enter Customer id:");
            Long customerId = Long.parseLong(scanner.nextLine());

            logger.info("Enter Animal name");
            String animal = scanner.nextLine();

            System.out.println("visit = " + visit);

            visitDataAccess.saveVisit(visit, customerId, animal);

        } catch (NumberFormatException e) {
            logger.info("Enter valid cost");
        }
    }
}