package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Property;
import com.realestate.rentalmanagement.repository.PropertyRepository;
import com.realestate.rentalmanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    @Transactional
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    @Transactional
    public Property updateProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
}
