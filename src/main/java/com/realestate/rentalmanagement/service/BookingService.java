package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    void deleteBooking(Long id);
    Optional<Booking> getBookingById(Long id);
    List<Booking> getAllBookings();
}
