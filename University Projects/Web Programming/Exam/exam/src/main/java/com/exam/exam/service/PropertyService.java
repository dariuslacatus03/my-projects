package com.exam.exam.service;

import com.exam.exam.model.Property;

import java.util.List;

public interface PropertyService {
    List<Property> getAllProperties();
    Property addProperty(Property property);
    Boolean deleteProperty(Integer propertyId);
}
