package com.hendzior.veterinary.dao;

import com.google.gson.Gson;
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
import java.util.List;

@Repository
public class JsonDatabaseAccess implements DatabaseAccess {

    private static final Logger logger = LoggerFactory.getLogger(JsonDatabaseAccess.class);
    private File file;
    private Gson gson = new Gson();

    public JsonDatabaseAccess(){

    }

    public JsonDatabaseAccess(File file) {
        this.file = file;
    }

    @Override
    public List<Customer> databaseRead() {

        logger.info("Reading file {}", file);
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.json"));

            Customer[] cusArray = gson.fromJson(reader, Customer[].class);
            List<Customer> custList = new ArrayList<>();
            custList.addAll(Arrays.asList(cusArray));

            logger.info("File {} read successfully", file);
            logger.trace("Customers list: {}", custList);
            return custList;

        } catch (FileNotFoundException e) {
            logger.error("File {} not found", file, e);

        }
        return null;
    }

    @Override
    public void databaseWrite(List<Customer> customersList) {
        logger.info("writing file : {}", file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.json"))) {

            gson.toJson(customersList, writer);

        } catch (IOException e) {

            logger.error("Error while writing file {}", file, e);
        }
        logger.info("File {} write successfully", file);
    }
}
