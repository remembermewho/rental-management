package com.realestate.rentalmanagement.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificationRequestDTO {
    // Идентификатор пользователя, которому отправляется уведомление
    @NotNull(message = "User ID обязателен")
    private Long userId;

    // Текст уведомления
    @NotBlank(message = "Сообщение уведомления не может быть пустым")
    private String message;

    // Тип уведомления (например, NEW_BOOKING, MESSAGE, UPDATE)
    @NotBlank(message = "Тип уведомления обязателен")
    private String type;

    public @NotNull(message = "User ID обязателен") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID обязателен") Long userId) {
        this.userId = userId;
    }

    public @NotBlank(message = "Сообщение уведомления не может быть пустым") String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank(message = "Сообщение уведомления не может быть пустым") String message) {
        this.message = message;
    }

    public @NotBlank(message = "Тип уведомления обязателен") String getType() {
        return type;
    }

    public void setType(@NotBlank(message = "Тип уведомления обязателен") String type) {
        this.type = type;
    }
}
