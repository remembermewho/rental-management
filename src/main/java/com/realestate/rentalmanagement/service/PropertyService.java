package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Property;
import com.realestate.rentalmanagement.payload.request.PropertyRequestDTO;
import com.realestate.rentalmanagement.payload.response.PropertyResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    PropertyResponseDTO createProperty(PropertyRequestDTO propertyRequestDTO);
    PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO propertyRequestDTO);
    PropertyResponseDTO getPropertyById(Long id);
    List<PropertyResponseDTO> getProperties(String region, String city, Integer numberOfRooms);
    boolean deleteProperty(Long id);
}
