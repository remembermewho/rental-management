package com.realestate.rentalmanagement.controller;

import com.realestate.rentalmanagement.payload.request.NotificationRequestDTO;
import com.realestate.rentalmanagement.payload.response.NotificationResponseDTO;
import com.realestate.rentalmanagement.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // GET /api/notifications?userId={userId}
    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getNotifications(@RequestParam Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    // PUT /api/notifications/{id}
    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> updateNotification(@PathVariable Long id,
                                                                      @Valid @RequestBody NotificationRequestDTO dto) {
        NotificationResponseDTO updated = notificationService.updateNotification(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE /api/notifications/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        boolean deleted = notificationService.deleteNotification(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        boolean updated = notificationService.markAsRead(id);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
