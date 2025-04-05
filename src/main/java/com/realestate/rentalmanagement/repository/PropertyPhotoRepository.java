package com.realestate.rentalmanagement.repository;

import com.realestate.rentalmanagement.entity.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {
    List<PropertyPhoto> findByPropertyId(Long propertyId);
}
