package com.realestate.rentalmanagement.repository;

import com.realestate.rentalmanagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByPropertyIdAndTenantIdAndStatusIn(Long propertyId, Long tenantId, List<String> statuses);

}
