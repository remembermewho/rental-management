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
import com.realestate.rentalmanagement.service.NotificationService;
import com.realestate.rentalmanagement.service.UserService;
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
    private final NotificationService notificationService;
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              PropertyRepository propertyRepository,
                              UserRepository userRepository, NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    // Маппинг Booking в BookingResponseDTO
    private BookingResponseDTO mapToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setTotalPrice(booking.getTotalPrice());
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
        booking.setTotalPrice(dto.getTotalPrice());

        return booking;
    }

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        // Проверка: уже есть активная заявка
        boolean exists = bookingRepository.existsByPropertyIdAndTenantIdAndStatusIn(
                bookingRequestDTO.getPropertyId(),
                bookingRequestDTO.getTenantId(),
                List.of("PENDING", "APPROVED")
        );
        if (exists) {
            throw new IllegalStateException("Вы уже подали заявку на этот объект");
        }
        Booking booking = mapToEntity(bookingRequestDTO);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        Booking saved = bookingRepository.save(booking);

        notificationService.createSystemNotification(
                booking.getProperty().getOwner().getId(),  // арендодатель
                "NEW_BOOKING",
                "Арендатор оставил заявку на аренду объекта ID: " + booking.getProperty().getId()
        );

        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public BookingResponseDTO updateBooking(Long id, BookingRequestDTO bookingRequestDTO) {
        return bookingRepository.findById(id).map(existing -> {
            existing.setStartDate(bookingRequestDTO.getStartDate());
            existing.setEndDate(bookingRequestDTO.getEndDate());
            existing.setTotalPrice(bookingRequestDTO.getTotalPrice());

            String oldStatus = existing.getStatus();
            String newStatus = bookingRequestDTO.getStatus();
            existing.setStatus(newStatus);
            existing.setUpdatedAt(LocalDateTime.now());

            Booking updated = bookingRepository.save(existing);

            // Уведомления по статусам
            if (!oldStatus.equals(newStatus)) {
                if ("APPROVED".equals(newStatus)) {
                    // ✅ Уведомление арендатору
                    notificationService.createSystemNotification(
                            existing.getTenant().getId(),
                            "BOOKING_APPROVED",
                            "Ваша заявка на аренду объекта ID: " + existing.getProperty().getId() + " была одобрена"
                    );
                    // Помечаем объект как арендованный
                    Property prop = existing.getProperty();
                    prop.setBooked(true);
                    propertyRepository.save(prop);

                } else if ("REJECTED".equals(newStatus)) {
                    notificationService.createSystemNotification(
                            existing.getTenant().getId(),
                            "BOOKING_REJECTED",
                            "Ваша заявка на аренду объекта ID: " + existing.getProperty().getId() + " была отклонена"
                    );
                } else if ("CANCELLED".equals(newStatus)) {
                    notificationService.createSystemNotification(
                            existing.getProperty().getOwner().getId(),
                            "BOOKING_CANCELLED",
                            "Арендатор отменил заявку на аренду объекта ID: " + existing.getProperty().getId()
                    );
                }
            }

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
    public List<BookingResponseDTO> getBookings(Long tenantId, Long propertyId, String status) {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .filter(b -> tenantId == null || b.getTenant().getId().equals(tenantId))
                .filter(b -> propertyId == null || b.getProperty().getId().equals(propertyId))
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
