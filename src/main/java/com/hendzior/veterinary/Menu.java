package com.hendzior.veterinary;

import com.hendzior.veterinary.dao.*;
import com.hendzior.veterinary.model.Animal;
import com.hendzior.veterinary.model.Customer;
import com.hendzior.veterinary.model.Visit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Slf4j
@Component
public class Menu {


    private String fileName1 = "customers.json";
    private String fileName2 = "animals.json";
    private String fileName3 = "visits.json";

    private File file1 = new File(fileName1);
    private File file2 = new File(fileName2);
    private File file3 = new File(fileName3);

    private CustomerDataAccess customerDataAccess;
    private ExternalDataAccess externalDataAccess;
    private VisitDataAccess visitDataAccess;
    private AnimalDataAccess animalDataAccess;
    private DataInputValidator dataInputValidator;

    public Menu(CustomerDataAccess customerDataAccess, ExternalDataAccess externalDataAccess, VisitDataAccess visitDataAccess, AnimalDataAccess animalDataAccess, DataInputValidator dataInputValidator) {
        this.customerDataAccess = customerDataAccess;
        this.animalDataAccess = animalDataAccess;
        this.externalDataAccess = externalDataAccess;
        this.visitDataAccess = visitDataAccess;
        this.dataInputValidator = dataInputValidator;
    }

    public void menu() {

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                log.info("Select option \n 1 = read local database \n 2 = Customers menu \n 3 = Animals menu \n 4 = Visits menu " +
                        "\n 6 = write to local database \n 0 = exit");

                String choice = scanner.nextLine();

                if (choice.equals("1")) {

                    externalDataAccess.importFile(file1);

                } else if (choice.equals("2")) {

                    customersMenu(scanner);

                } else if (choice.equals("3")) {

                    animalsMenu(scanner);

                } else if (choice.equals("4")) {

                    visitsMenu(scanner);

                } else if (choice.equals("6")) {


                    externalDataAccess.exportFile(customerDataAccess.findAll(), file1);
                    externalDataAccess.exportFile(animalDataAccess.findAll(), file2);
                    externalDataAccess.exportFile(visitDataAccess.findAll(), file3);

                } else if (choice.equals("9")) {
                    return;
                }
            }

        } catch (InputMismatchException e) {
            log.error("wrong data input", e);

        }
    }

    private void customersMenu(Scanner scanner) {
        try {
            log.info("Select option \n 1 = Add new customer \n 2 = Show all customers " +
                    "\n 3 = find a customer \n 4 = remove customer \n or any key = exit");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {

                createCustomer(scanner);

            } else if (choice.equals("2")) {

                log.info("customers list: " + customerDataAccess.findAll());

            } else if (choice.equals("3")) {

                log.info("Enter customer id: ");
                Long id = Long.parseLong(scanner.nextLine());

                log.info("Customer : " + customerDataAccess.findById(id));

            } else if (choice.equals("4")) {

                log.info("Enter customer id: ");
                Long id = Long.parseLong(scanner.nextLine());
                customerDataAccess.delete(customerDataAccess.findById(id).orElseThrow(() -> new NoSuchElementException(id.toString())));
            }
        } catch (NumberFormatException e) {
            log.error("Enter valid ID number!");
        } catch (NoSuchElementException id) {
            log.error("Customer with id {} not found!", id.getLocalizedMessage());
        }
    }


    private void createCustomer(Scanner scanner) {
        try {

            log.info("Enter last name:");
            String lName = scanner.nextLine();
            dataInputValidator.validateLength(lName);

            log.info("Enter name:");
            String name = scanner.nextLine();
            dataInputValidator.validateLength(name);

            log.info("Enter city:");
            String city = scanner.nextLine();
            dataInputValidator.validateLength(city);

            customerDataAccess.save(new Customer(name, lName, city));

        } catch (IllegalArgumentException e) {
            log.info("Wrong data entered");
        }
    }

    private void animalsMenu(Scanner scanner) {

        log.info("Select option \n 1 = Add new animal \n 2 = Remove animal \n 3 = Show all animals \n 4 = Show animal \n or any key = exit");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {

            createNewAnimal(scanner);

        } else if (choice.equals("2")) {
            try {
                log.info("Enter animal id:");
                Long id = Long.parseLong(scanner.nextLine());

                animalDataAccess.delete(animalDataAccess.findById(id).orElseThrow(() -> new NoSuchElementException(id.toString())));
            } catch (NumberFormatException e) {
                log.error("Enter valid ID number!");
            } catch (NoSuchElementException id) {
                log.error("Animal with id {} not found!", id.getLocalizedMessage());
            }

        } else if (choice.equals("3")) {

            log.info("All animals: " + animalDataAccess.findAll());

        } else if (choice.equals("4")) {

            log.info("Enter animal id:");
            Long id = Long.parseLong(scanner.nextLine());

            log.info("Animal: " + animalDataAccess.findById(id));
        }


    }

    private void createNewAnimal(Scanner scanner) {
        try {
            log.info("Enter animal name:");
            String aName = scanner.nextLine();
            dataInputValidator.validateLength(aName);
            log.info("Enter animal gender:");
            String aGender = scanner.nextLine();
            dataInputValidator.validateGen(aGender);

            log.info("Enter animal type:");
            String aType = scanner.nextLine();

            log.info("Enter animal year of birth:");
            String aAge = scanner.nextLine();
            dataInputValidator.validateDob(Integer.parseInt(aAge));

            log.info("enter owners id: ");
            Long id = Long.parseLong(scanner.nextLine());

            Animal animal = animalDataAccess.createAnimal(aName, aGender, aType, aAge, customerDataAccess.findById(id).orElseThrow(() -> new NoSuchElementException(id.toString())));
            animalDataAccess.save(animal);

        } catch (IllegalArgumentException e) {
            log.error("Wrong data entered");
        } catch (NoSuchElementException id) {
            log.error("Customer with id: {} not found!", id.getLocalizedMessage());
        }
    }

    private void visitsMenu(Scanner scanner) {

        log.info("Select option \n 1 = Add new visit \n 2 = Show all visits \n 3 = Show income from all visits \n or any key = exit");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            createVisit(scanner);

        } else if (choice.equals("2")) {

            log.info("All visits : {} ", visitDataAccess.findAll());

        } else if (choice.equals("3")) {

            log.info("Income from all visits = {}", visitDataAccess.incomeFromAllVisits());

        } else if (choice.equals("4")) try {

            log.info("Enter visit id:");
            Long id = Long.parseLong(scanner.nextLine());

            visitDataAccess.delete(visitDataAccess.findById(id).orElseThrow(() -> new NoSuchElementException(id.toString())));
        } catch (NoSuchElementException id) {
            log.error("Visit with id {} not found!", id.getLocalizedMessage());
        }
    }


    private void createVisit(Scanner scanner) {
        try {
            log.info("Enter visit description:");
            String description = scanner.nextLine();

            log.info("Enter visit cost");
            double cost = Double.parseDouble(scanner.nextLine());

            log.info("Enter Animal id:");
            Long id = Long.parseLong(scanner.nextLine());

            visitDataAccess.save(new Visit(description, cost, animalDataAccess.findById(id).orElseThrow(() -> new NoSuchElementException(id.toString()))));

        } catch (NumberFormatException e) {
            log.error("Enter valid visit cost or animal ID number!");
        } catch (NoSuchElementException id) {
            log.error("Animal with id {} not found!", id.getLocalizedMessage());
        }
    }
}