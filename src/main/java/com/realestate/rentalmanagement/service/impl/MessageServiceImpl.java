package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Message;
import com.realestate.rentalmanagement.repository.MessageRepository;
import com.realestate.rentalmanagement.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
