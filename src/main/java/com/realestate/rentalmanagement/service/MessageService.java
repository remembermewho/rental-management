package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.Message;
import com.realestate.rentalmanagement.payload.request.MessageRequestDTO;
import com.realestate.rentalmanagement.payload.response.MessageResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    MessageResponseDTO createMessage(MessageRequestDTO dto);
    MessageResponseDTO getMessageById(Long id);
    List<MessageResponseDTO> getMessages(Long senderId, Long receiverId, String conversationId);
    boolean deleteMessage(Long id);
}
