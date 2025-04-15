package com.realestate.rentalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "properties")

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Уникальный идентификатор объявления
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;



    // Обязательное поле: тип недвижимости (например, "Квартира", "Коммерческая недвижимость")
    @NotBlank(message = "Тип недвижимости обязателен")
    @Column(name = "property_type", nullable = false)
    private String propertyType;

    // Обязательное поле: количество комнат (минимум 1)
    @Min(value = 1, message = "Количество комнат должно быть не менее 1")
    @Column(name = "number_of_rooms", nullable = false)
    private int numberOfRooms;

    // Обязательное поле: серия дома (обозначение серии или корпуса)
    @NotBlank(message = "Серия дома обязательна")
    @Column(name = "house_series", nullable = false)
    private String houseSeries;

    // Обязательное поле: тип строения (например, "Кирпич", "Монолит", "Панельный")
    @NotBlank(message = "Тип строения обязателен")
    @Column(name = "building_type", nullable = false)
    private String buildingType;

    // Обязательное поле: этаж (номер этажа, не может быть отрицательным)
    @Min(value = 0, message = "Этаж не может быть отрицательным")
    @Column(nullable = false)
    private int floor;

    // Обязательное поле: площадь квартиры (общая площадь, должна быть больше 0)
    @NotNull(message = "Площадь обязательна")
    @DecimalMin(value = "0.1", inclusive = true, message = "Площадь должна быть больше 0")
    @Column(nullable = false)
    private BigDecimal area;

    // Обязательное поле: состояние объекта (например, "ремонт", "евро", "без ремонта")
    @NotBlank(message = "Состояние объекта обязательно")
    @Column(nullable = false)
    private String condition;

    // Обязательное поле: регион местоположения
    @NotBlank(message = "Регион обязателен")
    @Column(nullable = false)
    private String region;

    // Обязательное поле: город
    @NotBlank(message = "Город обязателен")
    @Column(nullable = false)
    private String city;

    // Обязательное поле: район (детальное местоположение внутри города)
    @NotBlank(message = "Район обязателен")
    @Column(nullable = false)
    private String district;

    // Обязательное поле: улица (часть адреса)
    @NotBlank(message = "Улица обязательна")
    @Column(nullable = false)
    private String street;

    // Обязательное поле: номер дома (часть адреса)
    @NotBlank(message = "Номер дома обязателен")
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    // Обязательное поле: долгота для указания места на карте
    @NotNull(message = "Долгота обязательна")
    @Column(nullable = false)
    private Double longitude;

    // Обязательное поле: широта для указания места на карте
    @NotNull(message = "Широта обязательна")
    @Column(nullable = false)
    private Double latitude;

    // Обязательное поле: цена аренды (должна быть больше 0)
    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.1", inclusive = true, message = "Цена должна быть больше 0")
    @Column(nullable = false)
    private BigDecimal price;

    // Опциональное поле: год сдачи в эксплуатацию
    @Column(name = "year_of_commissioning")
    private Integer yearOfCommissioning;

    // Опциональное поле: тип отопления
    @Column
    private String heating;

    // Опциональные характеристики объекта:
    // Наличие телефонной линии
    @Column
    private Boolean telephone;

    // Наличие интернета
    @Column
    private Boolean internet;

    // Тип санузла (например, "раздельный" или "совмещённый")
    @Column(name = "bathroom_type")
    private String bathroomType;

    // Наличие газоснабжения
    @Column
    private Boolean gas;

    // Наличие балкона
    @Column
    private Boolean balcony;

    // Обставленность квартиры (наличие мебели)
    @Column
    private Boolean furniture;

    // Наличие кондиционера
    @Column(name = "air_conditioner")
    private Boolean airConditioner;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyPhoto> photos;

    // Опциональное поле: текст объявления (подробное описание объекта)
    @Column(name = "announcement_text")
    private String announcementText;

    // Обязательное поле: дата создания объявления
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Обязательное поле: дата последнего обновления объявления
    @Column(nullable = false)
    private LocalDateTime updatedAt;



    public Property() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Тип недвижимости обязателен") String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(@NotBlank(message = "Тип недвижимости обязателен") String propertyType) {
        this.propertyType = propertyType;
    }

    @Min(value = 1, message = "Количество комнат должно быть не менее 1")
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(@Min(value = 1, message = "Количество комнат должно быть не менее 1") int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public @NotBlank(message = "Серия дома обязательна") String getHouseSeries() {
        return houseSeries;
    }

    public void setHouseSeries(@NotBlank(message = "Серия дома обязательна") String houseSeries) {
        this.houseSeries = houseSeries;
    }

    public @NotBlank(message = "Тип строения обязателен") String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(@NotBlank(message = "Тип строения обязателен") String buildingType) {
        this.buildingType = buildingType;
    }

    @Min(value = 0, message = "Этаж не может быть отрицательным")
    public int getFloor() {
        return floor;
    }

    public void setFloor(@Min(value = 0, message = "Этаж не может быть отрицательным") int floor) {
        this.floor = floor;
    }

    public @NotNull(message = "Площадь обязательна") @DecimalMin(value = "0.1", inclusive = true, message = "Площадь должна быть больше 0") BigDecimal getArea() {
        return area;
    }

    public void setArea(@NotNull(message = "Площадь обязательна") @DecimalMin(value = "0.1", inclusive = true, message = "Площадь должна быть больше 0") BigDecimal area) {
        this.area = area;
    }

    public @NotBlank(message = "Состояние объекта обязательно") String getCondition() {
        return condition;
    }

    public void setCondition(@NotBlank(message = "Состояние объекта обязательно") String condition) {
        this.condition = condition;
    }

    public @NotBlank(message = "Регион обязателен") String getRegion() {
        return region;
    }

    public void setRegion(@NotBlank(message = "Регион обязателен") String region) {
        this.region = region;
    }

    public @NotBlank(message = "Город обязателен") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "Город обязателен") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Район обязателен") String getDistrict() {
        return district;
    }

    public void setDistrict(@NotBlank(message = "Район обязателен") String district) {
        this.district = district;
    }

    public @NotBlank(message = "Улица обязательна") String getStreet() {
        return street;
    }

    public void setStreet(@NotBlank(message = "Улица обязательна") String street) {
        this.street = street;
    }

    public @NotBlank(message = "Номер дома обязателен") String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(@NotBlank(message = "Номер дома обязателен") String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public @NotNull(message = "Долгота обязательна") Double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NotNull(message = "Долгота обязательна") Double longitude) {
        this.longitude = longitude;
    }

    public @NotNull(message = "Широта обязательна") Double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NotNull(message = "Широта обязательна") Double latitude) {
        this.latitude = latitude;
    }

    public @NotNull(message = "Цена обязательна") @DecimalMin(value = "0.1", inclusive = true, message = "Цена должна быть больше 0") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Цена обязательна") @DecimalMin(value = "0.1", inclusive = true, message = "Цена должна быть больше 0") BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfCommissioning() {
        return yearOfCommissioning;
    }

    public void setYearOfCommissioning(Integer yearOfCommissioning) {
        this.yearOfCommissioning = yearOfCommissioning;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public Boolean getTelephone() {
        return telephone;
    }

    public void setTelephone(Boolean telephone) {
        this.telephone = telephone;
    }

    public Boolean getInternet() {
        return internet;
    }

    public void setInternet(Boolean internet) {
        this.internet = internet;
    }

    public String getBathroomType() {
        return bathroomType;
    }

    public void setBathroomType(String bathroomType) {
        this.bathroomType = bathroomType;
    }

    public Boolean getGas() {
        return gas;
    }

    public void setGas(Boolean gas) {
        this.gas = gas;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public Boolean getFurniture() {
        return furniture;
    }

    public void setFurniture(Boolean furniture) {
        this.furniture = furniture;
    }

    public Boolean getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public List<PropertyPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PropertyPhoto> photos) {
        this.photos = photos;
    }

    public String getAnnouncementText() {
        return announcementText;
    }

    public void setAnnouncementText(String announcementText) {
        this.announcementText = announcementText;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
