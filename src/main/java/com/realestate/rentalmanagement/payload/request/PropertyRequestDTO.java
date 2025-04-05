package com.realestate.rentalmanagement.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PropertyRequestDTO {
    // Обязательные поля
    @NotBlank(message = "Тип недвижимости обязателен")
    private String propertyType;

    @Min(value = 1, message = "Количество комнат должно быть не менее 1")
    private int numberOfRooms;

    @NotBlank(message = "Серия дома обязательна")
    private String houseSeries;

    @NotBlank(message = "Тип строения обязателен")
    private String buildingType;

    @Min(value = 0, message = "Этаж не может быть отрицательным")
    private int floor;

    @NotNull(message = "Площадь обязательна")
    @DecimalMin(value = "0.1", inclusive = true, message = "Площадь должна быть больше 0")
    private BigDecimal area;

    @NotBlank(message = "Состояние объекта обязательно")
    private String condition;

    @NotBlank(message = "Регион обязателен")
    private String region;

    @NotBlank(message = "Город обязателен")
    private String city;

    @NotBlank(message = "Район обязателен")
    private String district;

    @NotBlank(message = "Улица обязательна")
    private String street;

    @NotBlank(message = "Номер дома обязателен")
    private String houseNumber;

    @NotNull(message = "Долгота обязательна")
    private Double longitude;

    @NotNull(message = "Широта обязательна")
    private Double latitude;

    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.1", inclusive = true, message = "Цена должна быть больше 0")
    private BigDecimal price;

    // Опциональные поля
    private Integer yearOfCommissioning;
    private String heating;
    private Boolean telephone;
    private Boolean internet;
    private String bathroomType;
    private Boolean gas;
    private Boolean balcony;
    private Boolean furniture;
    private Boolean airConditioner;
    private String announcementText;

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getHouseSeries() {
        return houseSeries;
    }

    public void setHouseSeries(String houseSeries) {
        this.houseSeries = houseSeries;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public @DecimalMin(value = "0.1", inclusive = true, message = "Площадь должна быть больше 0") BigDecimal getArea() {
        return area;
    }

    public void setArea(@DecimalMin(value = "0.1", inclusive = true, message = "Площадь должна быть больше 0") BigDecimal area) {
        this.area = area;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public @DecimalMin(value = "0.1", inclusive = true, message = "Цена должна быть больше 0") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "0.1", inclusive = true, message = "Цена должна быть больше 0") BigDecimal price) {
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

    public String getAnnouncementText() {
        return announcementText;
    }

    public void setAnnouncementText(String announcementText) {
        this.announcementText = announcementText;
    }
}
