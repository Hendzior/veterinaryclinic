package com.hendzior.veterinary.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hendzior.veterinary.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class JsonDatabaseAccess implements DatabaseAccess {

    private static final Logger logger = LoggerFactory.getLogger(JsonDatabaseAccess.class);
      private Gson gson = new Gson();

    private CustomerDataAccess customerDataAccess;
    private AnimalDataAccess animalDataAccess;
    private VisitDataAccess visitDataAccess;


    public JsonDatabaseAccess(CustomerDataAccess customerDataAccess, AnimalDataAccess animalDataAccess, VisitDataAccess visitDataAccess) {
        this.customerDataAccess = customerDataAccess;
        this.animalDataAccess = animalDataAccess;
        this.visitDataAccess = visitDataAccess;
    }


    @Override
    public <T> List<T> databaseRead(File file) {

        populateCustomers(file);
//        logger.info("Reading file {}", file);
//        Gson gson = new Gson();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//
//            Customer[] cusArray = gson.fromJson(reader, Customer[].class);
//            List<Customer> custList = new ArrayList<>();
//            custList.addAll(Arrays.asList(cusArray));
//
//            logger.info("File {} read successfully", file);
//            logger.trace("Customers list: {}", custList);
//            return custList;
//
//        } catch (FileNotFoundException e) {
//            logger.error("File {} not found", file, e);
//
//        }
        return null;
    }

    @Override
    public <T> void databaseWrite(List<T> list, File file) {
        logger.info("writing file : {}", file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            gson.toJson(list, writer);


        } catch (IOException e) {

            logger.error("Error while writing file {}", file, e);
        }
        logger.info("File {} write successfully", file);
    }

    public void populateCustomers(File file) {
        logger.info("Reading file {}", file);
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            Customer[] cusArray = gson.fromJson(reader, Customer[].class);
            List<Customer> custList = new ArrayList<>();
            custList.addAll(Arrays.asList(cusArray));

            logger.info("File {} read successfully", file);
            logger.trace("Customers list: {}", custList);
            customerDataAccess.saveAll(custList);

        } catch (FileNotFoundException e) {
            logger.error("File {} not found", file, e);

        }

    }
}

