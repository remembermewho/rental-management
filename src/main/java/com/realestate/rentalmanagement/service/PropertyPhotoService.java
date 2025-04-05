package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.PropertyPhoto;

import java.util.List;
import java.util.Optional;

public interface PropertyPhotoService {
    PropertyPhoto createPropertyPhoto(PropertyPhoto propertyPhoto);
    PropertyPhoto updatePropertyPhoto(PropertyPhoto propertyPhoto);
    void deletePropertyPhoto(Long id);
    Optional<PropertyPhoto> getPropertyPhotoById(Long id);
    List<PropertyPhoto> getAllPropertyPhotos();
    List<PropertyPhoto> getPhotosByPropertyId(Long propertyId);
}
