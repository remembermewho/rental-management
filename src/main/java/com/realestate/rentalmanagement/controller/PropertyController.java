package com.realestate.rentalmanagement.controller;

import com.realestate.rentalmanagement.payload.request.PropertyRequestDTO;
import com.realestate.rentalmanagement.payload.response.PropertyResponseDTO;
import com.realestate.rentalmanagement.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // GET /api/properties?region=&city=&numberOfRooms=
    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> getProperties(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer numberOfRooms) {
        List<PropertyResponseDTO> properties = propertyService.getProperties(region, city, numberOfRooms);
        return ResponseEntity.ok(properties);
    }

    // GET /api/properties/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable Long id) {
        PropertyResponseDTO property = propertyService.getPropertyById(id);
        return property != null ? ResponseEntity.ok(property) : ResponseEntity.notFound().build();
    }

    // POST /api/properties
    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        PropertyResponseDTO created = propertyService.createProperty(propertyRequestDTO);
        return ResponseEntity.created(URI.create("/api/properties/" + created.getId())).body(created);
    }

    // PUT /api/properties/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        PropertyResponseDTO updated = propertyService.updateProperty(id, propertyRequestDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE /api/properties/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        boolean deleted = propertyService.deleteProperty(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
