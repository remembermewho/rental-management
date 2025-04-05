package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    Property createProperty(Property property);
    Property updateProperty(Property property);
    void deleteProperty(Long id);
    Optional<Property> getPropertyById(Long id);
    List<Property> getAllProperties();
}
