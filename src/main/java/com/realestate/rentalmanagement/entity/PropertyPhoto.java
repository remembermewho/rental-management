package com.realestate.rentalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "property_photos")
public class PropertyPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Уникальный идентификатор фото
    private Long id;

    // Обязательное поле: объект недвижимости, к которому относится фото
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    // Обязательное поле: путь к файлу фотографии
    @NotBlank(message = "Путь к фото обязателен")
    @Column(name = "photo_path", nullable = false)
    private String photoPath;

    // Опциональное поле: порядок отображения фото (если требуется сортировка)
    @Column(name = "display_order")
    private Integer displayOrder;

    // Обязательное поле: дата загрузки фото
    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;

    public PropertyPhoto() {}

    // Геттеры и сеттеры

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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
