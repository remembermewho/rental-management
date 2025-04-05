package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Property;
import com.realestate.rentalmanagement.payload.request.PropertyRequestDTO;
import com.realestate.rentalmanagement.payload.response.PropertyResponseDTO;
import com.realestate.rentalmanagement.repository.PropertyRepository;
import com.realestate.rentalmanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // Маппинг Property в PropertyResponseDTO
    private PropertyResponseDTO mapToDTO(Property property) {
        PropertyResponseDTO dto = new PropertyResponseDTO();
        dto.setId(property.getId());
        dto.setPropertyType(property.getPropertyType());
        dto.setNumberOfRooms(property.getNumberOfRooms());
        dto.setHouseSeries(property.getHouseSeries());
        dto.setBuildingType(property.getBuildingType());
        dto.setFloor(property.getFloor());
        dto.setArea(property.getArea());
        dto.setCondition(property.getCondition());
        dto.setRegion(property.getRegion());
        dto.setCity(property.getCity());
        dto.setDistrict(property.getDistrict());
        dto.setStreet(property.getStreet());
        dto.setHouseNumber(property.getHouseNumber());
        dto.setLongitude(property.getLongitude());
        dto.setLatitude(property.getLatitude());
        dto.setPrice(property.getPrice());
        dto.setYearOfCommissioning(property.getYearOfCommissioning());
        dto.setHeating(property.getHeating());
        dto.setTelephone(property.getTelephone());
        dto.setInternet(property.getInternet());
        dto.setBathroomType(property.getBathroomType());
        dto.setGas(property.getGas());
        dto.setBalcony(property.getBalcony());
        dto.setFurniture(property.getFurniture());
        dto.setAirConditioner(property.getAirConditioner());
        dto.setAnnouncementText(property.getAnnouncementText());
        dto.setCreatedAt(property.getCreatedAt());
        dto.setUpdatedAt(property.getUpdatedAt());
        // Если есть связь с фотографиями, можно здесь заполнить список путей
        // dto.setPhotoPaths(...);
        return dto;
    }

    // Маппинг PropertyRequestDTO в сущность Property
    private Property mapToEntity(PropertyRequestDTO dto) {
        Property property = new Property();
        property.setPropertyType(dto.getPropertyType());
        property.setNumberOfRooms(dto.getNumberOfRooms());
        property.setHouseSeries(dto.getHouseSeries());
        property.setBuildingType(dto.getBuildingType());
        property.setFloor(dto.getFloor());
        property.setArea(dto.getArea());
        property.setCondition(dto.getCondition());
        property.setRegion(dto.getRegion());
        property.setCity(dto.getCity());
        property.setDistrict(dto.getDistrict());
        property.setStreet(dto.getStreet());
        property.setHouseNumber(dto.getHouseNumber());
        property.setLongitude(dto.getLongitude());
        property.setLatitude(dto.getLatitude());
        property.setPrice(dto.getPrice());
        property.setYearOfCommissioning(dto.getYearOfCommissioning());
        property.setHeating(dto.getHeating());
        property.setTelephone(dto.getTelephone());
        property.setInternet(dto.getInternet());
        property.setBathroomType(dto.getBathroomType());
        property.setGas(dto.getGas());
        property.setBalcony(dto.getBalcony());
        property.setFurniture(dto.getFurniture());
        property.setAirConditioner(dto.getAirConditioner());
        property.setAnnouncementText(dto.getAnnouncementText());
        return property;
    }

    @Override
    @Transactional
    public PropertyResponseDTO createProperty(PropertyRequestDTO propertyRequestDTO) {
        Property property = mapToEntity(propertyRequestDTO);
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        Property created = propertyRepository.save(property);
        return mapToDTO(created);
    }

    @Override
    @Transactional
    public PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO propertyRequestDTO) {
        return propertyRepository.findById(id).map(existing -> {
            existing.setPropertyType(propertyRequestDTO.getPropertyType());
            existing.setNumberOfRooms(propertyRequestDTO.getNumberOfRooms());
            existing.setHouseSeries(propertyRequestDTO.getHouseSeries());
            existing.setBuildingType(propertyRequestDTO.getBuildingType());
            existing.setFloor(propertyRequestDTO.getFloor());
            existing.setArea(propertyRequestDTO.getArea());
            existing.setCondition(propertyRequestDTO.getCondition());
            existing.setRegion(propertyRequestDTO.getRegion());
            existing.setCity(propertyRequestDTO.getCity());
            existing.setDistrict(propertyRequestDTO.getDistrict());
            existing.setStreet(propertyRequestDTO.getStreet());
            existing.setHouseNumber(propertyRequestDTO.getHouseNumber());
            existing.setLongitude(propertyRequestDTO.getLongitude());
            existing.setLatitude(propertyRequestDTO.getLatitude());
            existing.setPrice(propertyRequestDTO.getPrice());
            existing.setYearOfCommissioning(propertyRequestDTO.getYearOfCommissioning());
            existing.setHeating(propertyRequestDTO.getHeating());
            existing.setTelephone(propertyRequestDTO.getTelephone());
            existing.setInternet(propertyRequestDTO.getInternet());
            existing.setBathroomType(propertyRequestDTO.getBathroomType());
            existing.setGas(propertyRequestDTO.getGas());
            existing.setBalcony(propertyRequestDTO.getBalcony());
            existing.setFurniture(propertyRequestDTO.getFurniture());
            existing.setAirConditioner(propertyRequestDTO.getAirConditioner());
            existing.setAnnouncementText(propertyRequestDTO.getAnnouncementText());
            existing.setUpdatedAt(LocalDateTime.now());
            Property updated = propertyRepository.save(existing);
            return mapToDTO(updated);
        }).orElse(null);
    }

    @Override
    public PropertyResponseDTO getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<PropertyResponseDTO> getProperties(String region, String city, Integer numberOfRooms) {
        List<Property> properties = propertyRepository.findAll();
        // Простейшая фильтрация (можно заменить на запрос в БД для лучшей производительности)
        return properties.stream()
                .filter(p -> region == null || p.getRegion().equalsIgnoreCase(region))
                .filter(p -> city == null || p.getCity().equalsIgnoreCase(city))
                .filter(p -> numberOfRooms == null || p.getNumberOfRooms() == numberOfRooms)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteProperty(Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
