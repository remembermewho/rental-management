package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Property;
import com.realestate.rentalmanagement.entity.PropertyPhoto;
import com.realestate.rentalmanagement.payload.request.PropertyPhotoRequestDTO;
import com.realestate.rentalmanagement.payload.response.PropertyPhotoResponseDTO;
import com.realestate.rentalmanagement.repository.PropertyPhotoRepository;
import com.realestate.rentalmanagement.repository.PropertyRepository;
import com.realestate.rentalmanagement.service.PropertyPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PropertyPhotoServiceImpl implements PropertyPhotoService {

    private final PropertyPhotoRepository propertyPhotoRepository;
    private final PropertyRepository propertyRepository;

    // Путь к корневой папке для сохранения фото
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/photos/";

    @Autowired
    public PropertyPhotoServiceImpl(PropertyPhotoRepository propertyPhotoRepository,
                                    PropertyRepository propertyRepository) {
        this.propertyPhotoRepository = propertyPhotoRepository;
        this.propertyRepository = propertyRepository;
    }

    private PropertyPhotoResponseDTO mapToDTO(PropertyPhoto photo) {
        PropertyPhotoResponseDTO dto = new PropertyPhotoResponseDTO();
        dto.setId(photo.getId());
        dto.setPhotoPath(photo.getPhotoPath());
        dto.setDisplayOrder(photo.getDisplayOrder());
        dto.setUploadDate(photo.getUploadDate());
        return dto;
    }

    @Override
    @Transactional
    public PropertyPhotoResponseDTO createPropertyPhoto(Long propertyId, MultipartFile file, PropertyPhotoRequestDTO dto) {
        // Проверяем наличие объекта недвижимости
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Объект недвижимости не найден"));
        // Генерируем уникальное имя файла с сохранением оригинального расширения
        String originalFilename = file.getOriginalFilename();
        String ext = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFilename = UUID.randomUUID().toString() + ext;
        // Формируем путь для сохранения файла, например, по дате
        String dateDir = LocalDateTime.now().toLocalDate().toString();
        String fullPath = uploadDir + dateDir + "/";
        File dir = new File(fullPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(fullPath + uniqueFilename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла", e);
        }
        // Создаем сущность PropertyPhoto
        PropertyPhoto photo = new PropertyPhoto();
        photo.setProperty(property);
        photo.setPhotoPath(fullPath + uniqueFilename);
        photo.setDisplayOrder(dto.getDisplayOrder());
        photo.setUploadDate(LocalDateTime.now());
        PropertyPhoto saved = propertyPhotoRepository.save(photo);
        return mapToDTO(saved);
    }

    @Override
    public List<PropertyPhotoResponseDTO> getPhotosByPropertyId(Long propertyId) {
        return propertyPhotoRepository.findByPropertyId(propertyId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deletePropertyPhoto(Long propertyId, Long photoId) {
        // Можно добавить проверку, что фото принадлежит указанному объекту
        if (propertyPhotoRepository.existsById(photoId)) {
            propertyPhotoRepository.deleteById(photoId);
            return true;
        }
        return false;
    }
}
