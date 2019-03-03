package com.hendzior.veterinary.dao;

import com.hendzior.veterinary.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
