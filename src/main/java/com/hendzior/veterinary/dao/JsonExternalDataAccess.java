package com.hendzior.veterinary.dao;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JsonExternalDataAccess implements ExternalDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(JsonExternalDataAccess.class);
    private Gson gson = new Gson();

    @Override
    public <T> List<T> importFile(File file, Type type) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            T[] readerArray = gson.fromJson(reader, type);
            List<T> readerList = new ArrayList<>(Arrays.asList(readerArray));

            logger.info("File {} read successfully", file);
            logger.info("Reader list: {}", readerList);
            return readerList;

        } catch (FileNotFoundException e) {
            logger.error("File {} not found", file, e);
            return null;
        }
    }

    @Override
    public <T> void exportFile(Iterable<T> list, File file) {
        logger.info("writing file : {}", file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            logger.info("Iterable : {}", list);
            gson.toJson(list, writer);

        } catch (IOException e) {

            logger.error("Error while writing file {}", file, e);
        }
        logger.info("File {} write successfully", file);
    }

}