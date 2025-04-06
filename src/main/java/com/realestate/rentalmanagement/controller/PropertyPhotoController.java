package com.realestate.rentalmanagement.controller;

import com.realestate.rentalmanagement.payload.request.PropertyPhotoRequestDTO;
import com.realestate.rentalmanagement.payload.response.PropertyPhotoResponseDTO;
import com.realestate.rentalmanagement.service.PropertyPhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/properties/{propertyId}/photos")
public class PropertyPhotoController {

    private final PropertyPhotoService propertyPhotoService;

    @Autowired
    public PropertyPhotoController(PropertyPhotoService propertyPhotoService) {
        this.propertyPhotoService = propertyPhotoService;
    }

    // GET /api/properties/{propertyId}/photos
    @GetMapping
    public ResponseEntity<List<PropertyPhotoResponseDTO>> getPhotos(@PathVariable Long propertyId) {
        List<PropertyPhotoResponseDTO> photos = propertyPhotoService.getPhotosByPropertyId(propertyId);
        return ResponseEntity.ok(photos);
    }

    // POST /api/properties/{propertyId}/photos
    // Обработка multipart/form-data запроса для загрузки фото
    @PostMapping
    public ResponseEntity<PropertyPhotoResponseDTO> uploadPhoto(@PathVariable Long propertyId,
                                                                @RequestPart("file") MultipartFile file,
                                                                @RequestPart(value = "metadata", required = false)
                                                                @Valid PropertyPhotoRequestDTO metadata) {
        if (metadata == null) {
            metadata = new PropertyPhotoRequestDTO();
        }
        PropertyPhotoResponseDTO created = propertyPhotoService.createPropertyPhoto(propertyId, file, metadata);
        return ResponseEntity.created(URI.create("/api/properties/" + propertyId + "/photos/" + created.getId()))
                .body(created);
    }

    // DELETE /api/properties/{propertyId}/photos/{photoId}
    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long propertyId, @PathVariable Long photoId) {
        boolean deleted = propertyPhotoService.deletePropertyPhoto(propertyId, photoId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
