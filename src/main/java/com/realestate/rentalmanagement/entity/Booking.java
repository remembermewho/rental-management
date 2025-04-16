package com.realestate.rentalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Уникальный идентификатор бронирования
    private Long id;

    // Обязательное поле: объект недвижимости, на который подана заявка
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    // Обязательное поле: арендатор, подающий заявку (сущность User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private User tenant;

    // Обязательное поле: дата начала аренды
    @NotNull(message = "Дата начала аренды обязательна")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    // Обязательное поле: дата окончания аренды
    @NotNull(message = "Дата окончания аренды обязательна")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // Обязательное поле: статус бронирования (например, "PENDING", "APPROVED", "REJECTED", "CANCELLED")

    @Column(nullable = false)
    private String status = "PENDING";

    // Обязательное поле: дата создания бронирования
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Обязательное поле: дата последнего обновления бронирования
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Booking() {}

    // Геттеры и сеттеры


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
