package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Notification;
import com.realestate.rentalmanagement.payload.request.NotificationRequestDTO;
import com.realestate.rentalmanagement.payload.response.NotificationResponseDTO;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    NotificationResponseDTO createNotification(NotificationRequestDTO dto);
    NotificationResponseDTO updateNotification(Long id, NotificationRequestDTO dto);
    NotificationResponseDTO getNotificationById(Long id);
    List<NotificationResponseDTO> getNotificationsByUserId(Long userId);
    boolean deleteNotification(Long id);
}
