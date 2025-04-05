package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message createMessage(Message message);
    Message updateMessage(Message message);
    void deleteMessage(Long id);
    Optional<Message> getMessageById(Long id);
    List<Message> getAllMessages();
}
