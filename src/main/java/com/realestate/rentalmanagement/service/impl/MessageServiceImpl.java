package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Message;
import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.payload.request.MessageRequestDTO;
import com.realestate.rentalmanagement.payload.response.MessageResponseDTO;
import com.realestate.rentalmanagement.repository.MessageRepository;
import com.realestate.rentalmanagement.repository.UserRepository;
import com.realestate.rentalmanagement.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    private MessageResponseDTO mapToDTO(Message message) {
        MessageResponseDTO dto = new MessageResponseDTO();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setContent(message.getContent());
        dto.setSentAt(message.getSentAt());
        dto.setRead(message.isRead());
        dto.setConversationId(message.getConversationId());
        return dto;
    }

    @Override
    @Transactional
    public MessageResponseDTO createMessage(MessageRequestDTO dto) {
        Message message = new Message();
        // Загрузка отправителя и получателя
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Отправитель не найден"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Получатель не найден"));
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.getContent());
        message.setConversationId(dto.getConversationId());
        message.setSentAt(LocalDateTime.now());
        message.setRead(false);
        Message saved = messageRepository.save(message);
        return mapToDTO(saved);
    }

    @Override
    public MessageResponseDTO getMessageById(Long id) {
        return messageRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<MessageResponseDTO> getMessages(Long senderId, Long receiverId, String conversationId) {
        List<Message> messages = messageRepository.findAll();
        // Простейшая фильтрация; в реальном проекте лучше использовать запросы к БД
        return messages.stream()
                .filter(m -> senderId == null || m.getSender().getId().equals(senderId))
                .filter(m -> receiverId == null || m.getReceiver().getId().equals(receiverId))
                .filter(m -> conversationId == null || (m.getConversationId() != null &&
                        m.getConversationId().equalsIgnoreCase(conversationId)))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteMessage(Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
