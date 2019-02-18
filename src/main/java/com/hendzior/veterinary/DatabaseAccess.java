package com.hendzior.veterinary;

import java.util.List;

public interface DatabaseAccess {

    List<Customer> databaseRead();

    void databaseWrite(List<Customer> customersList);

}
