package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Booking;
import com.realestate.rentalmanagement.entity.Property;
import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.payload.request.BookingRequestDTO;
import com.realestate.rentalmanagement.payload.response.BookingResponseDTO;
import com.realestate.rentalmanagement.repository.BookingRepository;
import com.realestate.rentalmanagement.repository.PropertyRepository;
import com.realestate.rentalmanagement.repository.UserRepository;
import com.realestate.rentalmanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              PropertyRepository propertyRepository,
                              UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    // Маппинг Booking в BookingResponseDTO
    private BookingResponseDTO mapToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setPropertyId(booking.getProperty().getId());
        dto.setTenantId(booking.getTenant().getId());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setStatus(booking.getStatus());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setUpdatedAt(booking.getUpdatedAt());
        return dto;
    }

    // Маппинг BookingRequestDTO в сущность Booking (загрузка Property и Tenant)
    private Booking mapToEntity(BookingRequestDTO dto) {
        Booking booking = new Booking();
        // Найти объект недвижимости по ID
        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Объект недвижимости не найден"));
        // Найти арендатора по ID
        User tenant = userRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new RuntimeException("Арендатор не найден"));
        booking.setProperty(property);
        booking.setTenant(tenant);
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setStatus(dto.getStatus());
        return booking;
    }

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        Booking booking = mapToEntity(bookingRequestDTO);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        Booking saved = bookingRepository.save(booking);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public BookingResponseDTO updateBooking(Long id, BookingRequestDTO bookingRequestDTO) {
        return bookingRepository.findById(id).map(existing -> {
            // Обычно объект недвижимости и арендатора менять не требуется,
            // поэтому обновляем только даты и статус.
            existing.setStartDate(bookingRequestDTO.getStartDate());
            existing.setEndDate(bookingRequestDTO.getEndDate());
            existing.setStatus(bookingRequestDTO.getStatus());
            existing.setUpdatedAt(LocalDateTime.now());
            Booking updated = bookingRepository.save(existing);
            return mapToDTO(updated);
        }).orElse(null);
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<BookingResponseDTO> getBookings(Long tenantId, String status) {
        // Простейшая фильтрация: если tenantId или статус переданы, фильтруем результаты
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .filter(b -> tenantId == null || b.getTenant().getId().equals(tenantId))
                .filter(b -> status == null || b.getStatus().equalsIgnoreCase(status))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
