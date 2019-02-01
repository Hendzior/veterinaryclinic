package com.hendzior.veterinary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Year;


public class DataInputValidator {
private static final Logger logger = LoggerFactory.getLogger(DataInputValidator.class);


    public void customerValidator(String str) {
        if (str.length() < 2 || str.length() > 15 || str.isEmpty()) {

            logger.info("data input to short or too long");
            throw new IllegalArgumentException();
        }
    }

    public void validateGen(String gender) {
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female") || gender.isEmpty()) {

            logger.info("data input is not male or female");
            throw new IllegalArgumentException();
        }
    }

    public void validateDob(int dob){
        if(dob> Year.now().getValue()||dob<1980){

            logger.info("data input must be a number from 1980 to: "+Year.now().getValue() );
            throw new IllegalArgumentException();

        }
    }
}
