package com.realestate.rentalmanagement.scheduler;

import com.realestate.rentalmanagement.entity.Booking;
import com.realestate.rentalmanagement.service.NotificationService;
import com.realestate.rentalmanagement.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RentalReminderTask {

    private final BookingRepository bookingRepository;
    private final NotificationService notificationService;

    @Autowired
    public RentalReminderTask(BookingRepository bookingRepository,
                              NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.notificationService = notificationService;
    }

    // Запускается каждый день в 8:00
    @Scheduled(cron = "0 0 8 * * *")
    public void sendReminders() {
        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.plusDays(3);

        List<Booking> bookings = bookingRepository.findAll().stream()
                .filter(b -> "APPROVED".equalsIgnoreCase(b.getStatus()))
                .filter(b -> b.getEndDate().equals(targetDate))
                .toList();

        for (Booking booking : bookings) {
            Long userId = booking.getTenant().getId();
            String message = "До окончания вашей аренды по объекту №" +
                    booking.getProperty().getId() + " осталось 3 дня.";

            notificationService.createSystemNotification(userId, "REMINDER", message);
        }
    }
}
