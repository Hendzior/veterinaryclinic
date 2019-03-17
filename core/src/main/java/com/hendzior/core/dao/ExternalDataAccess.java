package com.hendzior.core.dao;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;


public interface ExternalDataAccess {

    <T> List<T> importFile(File file, Type type);

    <T> void exportFile(Iterable<T> list, File file);

}
