package com.realestate.rentalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Уникальный идентификатор уведомления
    private Long id;

    // Обязательное поле: пользователь, которому отправлено уведомление (сущность User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Обязательное поле: текст уведомления
    @NotBlank(message = "Текст уведомления не может быть пустым")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    // Обязательное поле: тип уведомления (например, "NEW_BOOKING", "MESSAGE", "UPDATE")
    @NotBlank(message = "Тип уведомления обязателен")
    @Column(nullable = false)
    private String type;

    // Флаг, указывающий, прочитано уведомление или нет
    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    // Обязательное поле: дата создания уведомления
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Notification() {}

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
