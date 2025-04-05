package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.PropertyPhoto;
import com.realestate.rentalmanagement.repository.PropertyPhotoRepository;
import com.realestate.rentalmanagement.service.PropertyPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyPhotoServiceImpl implements PropertyPhotoService {

    private final PropertyPhotoRepository propertyPhotoRepository;

    @Autowired
    public PropertyPhotoServiceImpl(PropertyPhotoRepository propertyPhotoRepository) {
        this.propertyPhotoRepository = propertyPhotoRepository;
    }

    @Override
    @Transactional
    public PropertyPhoto createPropertyPhoto(PropertyPhoto propertyPhoto) {
        return propertyPhotoRepository.save(propertyPhoto);
    }

    @Override
    @Transactional
    public PropertyPhoto updatePropertyPhoto(PropertyPhoto propertyPhoto) {
        return propertyPhotoRepository.save(propertyPhoto);
    }

    @Override
    @Transactional
    public void deletePropertyPhoto(Long id) {
        propertyPhotoRepository.deleteById(id);
    }

    @Override
    public Optional<PropertyPhoto> getPropertyPhotoById(Long id) {
        return propertyPhotoRepository.findById(id);
    }

    @Override
    public List<PropertyPhoto> getAllPropertyPhotos() {
        return propertyPhotoRepository.findAll();
    }

    @Override
    public List<PropertyPhoto> getPhotosByPropertyId(Long propertyId) {
        return propertyPhotoRepository.findByPropertyId(propertyId);
    }
}
