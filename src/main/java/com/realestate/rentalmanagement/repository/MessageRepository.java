package com.realestate.rentalmanagement.repository;

import com.realestate.rentalmanagement.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
