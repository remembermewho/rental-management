package com.realestate.rentalmanagement.repository;

import com.realestate.rentalmanagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
