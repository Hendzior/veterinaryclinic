package com.hendzior.veterinary.dao;

import java.io.File;
import java.util.List;


public interface DatabaseAccess {

    <T> List<T> databaseRead(File file);

    <T> void databaseWrite(List<T> list, File file);

}
