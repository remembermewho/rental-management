package com.realestate.rentalmanagement;

import com.realestate.rentalmanagement.entity.Booking;
import com.realestate.rentalmanagement.entity.Property;
import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.payload.request.BookingRequestDTO;
import com.realestate.rentalmanagement.payload.response.BookingResponseDTO;
import com.realestate.rentalmanagement.repository.BookingRepository;
import com.realestate.rentalmanagement.repository.PropertyRepository;
import com.realestate.rentalmanagement.repository.UserRepository;
import com.realestate.rentalmanagement.service.NotificationService;
import com.realestate.rentalmanagement.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {
    @Mock private BookingRepository bookingRepository;
    @Mock private PropertyRepository propertyRepository;
    @Mock private UserRepository userRepository;
    @Mock private NotificationService notificationService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void createBooking_shouldCreateBookingAndNotifyOwner() {
        // arrange
        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setPropertyId(1L);
        dto.setTenantId(2L);
        dto.setStartDate(LocalDate.of(2025, 6, 1));
        dto.setEndDate(LocalDate.of(2025, 6, 5));
        dto.setTotalPrice(4000.0);

        Property property = new Property();
        property.setId(1L);
        User owner = new User();
        owner.setId(99L);
        property.setOwner(owner);

        User tenant = new User();
        tenant.setId(2L);

        Booking savedBooking = new Booking();
        savedBooking.setId(123L);
        savedBooking.setProperty(property);
        savedBooking.setTenant(tenant);
        savedBooking.setStartDate(dto.getStartDate());
        savedBooking.setEndDate(dto.getEndDate());
        savedBooking.setTotalPrice(dto.getTotalPrice());

        when(bookingRepository.existsByPropertyIdAndTenantIdAndStatusIn(
                eq(1L), eq(2L), anyList()
        )).thenReturn(false);
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
        when(userRepository.findById(2L)).thenReturn(Optional.of(tenant));

        // <-- здесь важно: any(Booking.class) из Mockito
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        // act
        BookingResponseDTO response = bookingService.createBooking(dto);

        // assert
        assertNotNull(response, "Должен вернуться объект ответа");
        assertEquals(123L, response.getId(), "ID ответа совпадает с сохранённым");
        assertEquals(4000.0, response.getTotalPrice(), "Цена совпадает");
        verify(notificationService, times(1))
                .createSystemNotification(eq(99L), eq("NEW_BOOKING"), contains("ID: 1"));
    }

    @Test
    void updateBooking_whenStatusApproved_shouldNotifyTenantAndMarkProperty() {
        // arrange
        Booking existing = new Booking();
        existing.setId(1L);
        existing.setStatus("PENDING");
        existing.setStartDate(LocalDate.of(2025,6,1));
        existing.setEndDate(LocalDate.of(2025,6,5));
        existing.setTotalPrice(4000.0);

        Property prop = new Property();
        prop.setId(10L);
        User owner = new User(); owner.setId(100L);
        prop.setOwner(owner);
        existing.setProperty(prop);

        User tenant = new User(); tenant.setId(200L);
        existing.setTenant(tenant);

        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setStartDate(existing.getStartDate());
        dto.setEndDate(existing.getEndDate());
        dto.setTotalPrice(existing.getTotalPrice());
        dto.setStatus("APPROVED");

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(inv -> inv.getArgument(0));

        // act
        BookingResponseDTO result = bookingService.updateBooking(1L, dto);

        // assert
        assertNotNull(result);
        assertEquals("APPROVED", result.getStatus(), "Статус должен быть APPROVED");
        verify(notificationService, times(1))
                .createSystemNotification(eq(200L), eq("BOOKING_APPROVED"), contains("одобрена"));
        verify(propertyRepository, times(1)).save(any(Property.class));
    }
}
