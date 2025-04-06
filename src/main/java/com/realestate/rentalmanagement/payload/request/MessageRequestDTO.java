package com.realestate.rentalmanagement.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageRequestDTO {
    // Идентификатор отправителя
    @NotNull(message = "Sender ID обязателен")
    private Long senderId;

    // Идентификатор получателя
    @NotNull(message = "Receiver ID обязателен")
    private Long receiverId;

    // Содержание сообщения
    @NotBlank(message = "Содержание сообщения не может быть пустым")
    private String content;

    // Опционально: идентификатор беседы для группировки сообщений
    private String conversationId;

    public @NotNull(message = "Sender ID обязателен") Long getSenderId() {
        return senderId;
    }

    public void setSenderId(@NotNull(message = "Sender ID обязателен") Long senderId) {
        this.senderId = senderId;
    }

    public @NotNull(message = "Receiver ID обязателен") Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(@NotNull(message = "Receiver ID обязателен") Long receiverId) {
        this.receiverId = receiverId;
    }

    public @NotBlank(message = "Содержание сообщения не может быть пустым") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "Содержание сообщения не может быть пустым") String content) {
        this.content = content;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
