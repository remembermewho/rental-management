package com.realestate.rentalmanagement.payload.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingRequestDTO {
    // Идентификатор объекта недвижимости, на который подается заявка
    @NotNull(message = "Property ID обязателен")
    private Long propertyId;

    // Идентификатор арендатора (пользователь)
    @NotNull(message = "Tenant ID обязателен")
    private Long tenantId;

    // Дата начала аренды
    @NotNull(message = "Дата начала аренды обязательна")
    private LocalDate startDate;

    // Дата окончания аренды
    @NotNull(message = "Дата окончания аренды обязательна")
    private LocalDate endDate;

    @NotNull(message = "Итоговая сумма обязательна")
    private Double totalPrice;

    @NotNull
    private String status;

    public @NotNull(message = "Property ID обязателен") Long getPropertyId() {
        return propertyId;
    }

    public @NotNull Double getTotalPrice() {
        return totalPrice;
    }

    public @NotNull String getStatus() {
        return status;
    }

    public void setStatus(@NotNull String status) {
        this.status = status;
    }

    public void setTotalPrice(@NotNull Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPropertyId(@NotNull(message = "Property ID обязателен") Long propertyId) {
        this.propertyId = propertyId;
    }

    public @NotNull(message = "Tenant ID обязателен") Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(@NotNull(message = "Tenant ID обязателен") Long tenantId) {
        this.tenantId = tenantId;
    }

    public @NotNull(message = "Дата начала аренды обязательна") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "Дата начала аренды обязательна") LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull(message = "Дата окончания аренды обязательна") LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = "Дата окончания аренды обязательна") LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BookingRequestDTO{" +
                "propertyId=" + propertyId +
                ", tenantId=" + tenantId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
