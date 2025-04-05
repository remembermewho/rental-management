package com.realestate.rentalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Уникальный идентификатор сообщения
    private Long id;

    // Обязательное поле: отправитель сообщения (сущность User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // Обязательное поле: получатель сообщения (сущность User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // Обязательное поле: содержание сообщения
    @NotBlank(message = "Содержание сообщения не может быть пустым")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // Обязательное поле: дата и время отправки сообщения
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    // Флаг, указывающий, прочитано сообщение или нет
    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    // Опциональное поле: идентификатор беседы для группировки сообщений
    @Column(name = "conversation_id")
    private String conversationId;

    public Message() {}

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
