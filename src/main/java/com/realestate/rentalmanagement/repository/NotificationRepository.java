package com.realestate.rentalmanagement.repository;

import com.realestate.rentalmanagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
