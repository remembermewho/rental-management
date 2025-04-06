package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.PropertyPhoto;
import com.realestate.rentalmanagement.payload.request.PropertyPhotoRequestDTO;
import com.realestate.rentalmanagement.payload.response.PropertyPhotoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PropertyPhotoService {
    PropertyPhotoResponseDTO createPropertyPhoto(Long propertyId, MultipartFile file, PropertyPhotoRequestDTO dto);
    List<PropertyPhotoResponseDTO> getPhotosByPropertyId(Long propertyId);
    boolean deletePropertyPhoto(Long propertyId, Long photoId);
}
