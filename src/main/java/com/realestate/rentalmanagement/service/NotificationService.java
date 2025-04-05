package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Notification updateNotification(Notification notification);
    void deleteNotification(Long id);
    Optional<Notification> getNotificationById(Long id);
    List<Notification> getAllNotifications();
}
