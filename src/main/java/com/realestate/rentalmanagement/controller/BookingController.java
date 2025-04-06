package com.realestate.rentalmanagement.controller;

import com.realestate.rentalmanagement.payload.request.BookingRequestDTO;
import com.realestate.rentalmanagement.payload.response.BookingResponseDTO;
import com.realestate.rentalmanagement.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // GET /api/bookings?tenantId=&status=
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getBookings(
            @RequestParam(required = false) Long tenantId,
            @RequestParam(required = false) String status) {
        List<BookingResponseDTO> bookings = bookingService.getBookings(tenantId, status);
        return ResponseEntity.ok(bookings);
    }

    // GET /api/bookings/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        BookingResponseDTO booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    // POST /api/bookings
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO created = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.created(URI.create("/api/bookings/" + created.getId())).body(created);
    }

    // PUT /api/bookings/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id,
                                                            @Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO updated = bookingService.updateBooking(id, bookingRequestDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE /api/bookings/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
