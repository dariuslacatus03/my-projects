package com.exam.exam.repository;

import com.exam.exam.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Property findPropertyByAddress(String address);
}
