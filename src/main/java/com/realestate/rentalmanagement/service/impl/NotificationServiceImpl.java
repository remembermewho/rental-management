package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Notification;
import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.payload.request.NotificationRequestDTO;
import com.realestate.rentalmanagement.payload.response.NotificationResponseDTO;
import com.realestate.rentalmanagement.repository.NotificationRepository;
import com.realestate.rentalmanagement.repository.UserRepository;
import com.realestate.rentalmanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    private NotificationResponseDTO mapToDTO(Notification notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setUserId(notification.getUser().getId());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setRead(notification.isRead());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }

    @Override
    @Transactional
    public NotificationResponseDTO createNotification(NotificationRequestDTO dto) {
        Notification notification = new Notification();
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        notification.setUser(user);
        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        Notification saved = notificationRepository.save(notification);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public NotificationResponseDTO updateNotification(Long id, NotificationRequestDTO dto) {
        return notificationRepository.findById(id).map(existing -> {
            // Обновляем поля уведомления; обычно меняют статус прочтения и/или сообщение
            existing.setMessage(dto.getMessage());
            existing.setType(dto.getType());
            // Например, можно установить isRead = true, если требуется
            existing.setRead(true);
            Notification updated = notificationRepository.save(existing);
            return mapToDTO(updated);
        }).orElse(null);
    }

    @Override
    public NotificationResponseDTO getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .filter(n -> n.getUser().getId().equals(userId))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean markAsRead(Long id) {
        return notificationRepository.findById(id).map(n -> {
            n.setRead(true);
            notificationRepository.save(n);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public void createSystemNotification(Long userId, String type, String message) {
        NotificationRequestDTO dto = new NotificationRequestDTO();
        dto.setUserId(userId);
        dto.setType(type);
        dto.setMessage(message);
        createNotification(dto);
    }

    @Override
    @Transactional
    public void deleteAllByUser(Long userId) {
        notificationRepository.deleteAllByUserId(userId);
    }
}
