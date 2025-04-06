package com.realestate.rentalmanagement.controller;

import com.realestate.rentalmanagement.payload.request.MessageRequestDTO;
import com.realestate.rentalmanagement.payload.response.MessageResponseDTO;
import com.realestate.rentalmanagement.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // GET /api/messages?senderId=&receiverId=&conversationId=
    @GetMapping
    public ResponseEntity<List<MessageResponseDTO>> getMessages(
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Long receiverId,
            @RequestParam(required = false) String conversationId) {
        List<MessageResponseDTO> messages = messageService.getMessages(senderId, receiverId, conversationId);
        return ResponseEntity.ok(messages);
    }

    // GET /api/messages/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> getMessageById(@PathVariable Long id) {
        MessageResponseDTO message = messageService.getMessageById(id);
        return message != null ? ResponseEntity.ok(message) : ResponseEntity.notFound().build();
    }

    // POST /api/messages
    @PostMapping
    public ResponseEntity<MessageResponseDTO> createMessage(@Valid @RequestBody MessageRequestDTO dto) {
        MessageResponseDTO created = messageService.createMessage(dto);
        return ResponseEntity.created(URI.create("/api/messages/" + created.getId())).body(created);
    }

    // DELETE /api/messages/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        boolean deleted = messageService.deleteMessage(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
