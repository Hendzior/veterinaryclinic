package com.hendzior.veterinary.core.dao;

import com.hendzior.veterinary.core.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
