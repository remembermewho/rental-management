package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Booking;
import com.realestate.rentalmanagement.payload.request.BookingRequestDTO;
import com.realestate.rentalmanagement.payload.response.BookingResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO);
    BookingResponseDTO updateBooking(Long id, BookingRequestDTO bookingRequestDTO);
    BookingResponseDTO getBookingById(Long id);
    List<BookingResponseDTO> getBookings(Long tenantId, String status);
    boolean deleteBooking(Long id);
}
