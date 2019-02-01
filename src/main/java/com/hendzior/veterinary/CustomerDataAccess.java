package com.hendzior.veterinary;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CustomerDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDataAccess.class);

    private File file;

    public CustomerDataAccess(File file) {
        this.file = file;
    }

    Gson gson = new Gson();

    public void jsonFileWriter(List<Customer> customersList) {

        logger.info("writing file : {}", file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            gson.toJson(customersList, writer);

        } catch (IOException e) {

            logger.error("Error while writing file {}", file, e);
        }
        logger.info("File {} write successfully", file);
    }


    public List<Customer> jsonFileReader() {

        logger.info("Reading file {}", file);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            Customer[] cusArray = gson.fromJson(reader, Customer[].class);
            List<Customer> custList = new ArrayList<>();
            custList.addAll(Arrays.asList(cusArray));

            logger.info("File {} read successfully", file);
            return custList;

        } catch (FileNotFoundException e) {
            logger.error("File {} not found", file, e);

        }
        return null;
    }

    public Customer findCustomerByLname(String lName, List<Customer> customerList) {

        for (Customer customer : customerList) {

            if (customer.getLastName().equalsIgnoreCase(lName)) {

                return customer;
            }
        }
        return null;
    }

    public Customer addNewCustomer() {

        try {
            Scanner scanner = new Scanner(System.in);
            DataInputValidator dataInputValidator = new DataInputValidator();


            logger.info("Enter last name:");
            String lName = scanner.nextLine();
            dataInputValidator.customerValidator(lName);


            logger.info("Enter name:");
            String name = scanner.nextLine();
            dataInputValidator.customerValidator(name);

            logger.info("Enter city:");
            String city = scanner.nextLine();
            dataInputValidator.customerValidator(city);

            logger.info("Enter animal name:");
            String aName = scanner.nextLine();
            dataInputValidator.customerValidator(aName);
            logger.info("Enter animal gender:");
            String aGender = scanner.nextLine();
            dataInputValidator.validateGen(aGender);

            logger.info("Enter animal type:");
            String aType = scanner.nextLine();

            logger.info("Enter animal year of birth:");
            String aAge = scanner.nextLine();
            dataInputValidator.validateDob(Integer.parseInt(aAge));

            Animal animal = createAnimal(aName,aGender,Integer.parseInt(aAge),aType);
            List<Animal> animalList = new ArrayList<>();
            animalList.add(animal);
            Customer customer = new Customer(name, lName, city, animalList);

            return customer;

        } catch (IllegalArgumentException e) {

            logger.error("Wrong data", e);

            return null;
        }

    }

    public static Animal createAnimal(String name, String gender, int age, String type) {

        switch (type.toLowerCase()) {

            case "dog":

                return new Dog(name, gender, age, type);

            case "cat":
                return new Cat(name, gender, age, type);

            default:
                return new Animal(name, gender, age, type);

        }

    }

}
