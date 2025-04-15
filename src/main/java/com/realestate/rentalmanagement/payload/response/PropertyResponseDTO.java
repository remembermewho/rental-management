package com.realestate.rentalmanagement.payload.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PropertyResponseDTO {
    private Long id;
    private Long ownerId;
    private String propertyType;
    private int numberOfRooms;
    private String houseSeries;
    private String buildingType;
    private int floor;
    private BigDecimal area;
    private String condition;
    private String region;
    private String city;
    private String district;
    private String street;
    private String houseNumber;
    private Double longitude;
    private Double latitude;
    private BigDecimal price;

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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Список путей к фотографиям объекта
    private List<String> photoPaths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public List<String> getPhotoPaths() {
        return photoPaths;
    }

    public void setPhotoPaths(List<String> photoPaths) {
        this.photoPaths = photoPaths;
    }
}
