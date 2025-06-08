package com.realestate.rentalmanagement;

import com.realestate.rentalmanagement.entity.Property;
import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.payload.request.PropertyRequestDTO;
import com.realestate.rentalmanagement.payload.response.PropertyResponseDTO;
import com.realestate.rentalmanagement.repository.PropertyRepository;
import com.realestate.rentalmanagement.repository.UserRepository;
import com.realestate.rentalmanagement.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {
    @Mock private PropertyRepository propertyRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    private PropertyRequestDTO buildFullDto() {
        PropertyRequestDTO dto = new PropertyRequestDTO();
        dto.setPropertyType("Apartment");
        dto.setNumberOfRooms(3);
        dto.setHouseSeries("SeriesA");
        dto.setBuildingType("Brick");
        dto.setFloor(5);
        dto.setArea(BigDecimal.valueOf(75.5));
        dto.setCondition("Good");
        dto.setRegion("Chuy");
        dto.setCity("Bishkek");
        dto.setDistrict("Lenin");
        dto.setStreet("Oak Street");
        dto.setHouseNumber("12B");
        dto.setLongitude(74.587);
        dto.setLatitude(42.8746);
        dto.setPrice(BigDecimal.valueOf(1200.0));
        dto.setYearOfCommissioning(2015);
        dto.setHeating("Central");
        dto.setTelephone(true);
        dto.setInternet(true);
        dto.setBathroomType("Combined");
        dto.setGas(true);
        dto.setBalcony(true);
        dto.setFurniture(true);
        dto.setAirConditioner(true);
        dto.setAnnouncementText("Spacious and bright apartment");
        return dto;
    }

    @Test
    void createProperty_shouldSaveAndReturnDTO() {
        // arrange
        PropertyRequestDTO dto = buildFullDto();

        User user = new User();
        user.setId(42L);
        user.setUsername("alice");

        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("alice");
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));

        Property saved = new Property();
        saved.setId(100L);
        saved.setOwner(user);
        saved.setBooked(false);
        saved.setPropertyType(dto.getPropertyType());
        saved.setNumberOfRooms(dto.getNumberOfRooms());
        saved.setHouseSeries(dto.getHouseSeries());
        saved.setBuildingType(dto.getBuildingType());
        saved.setFloor(dto.getFloor());
        saved.setArea(dto.getArea());
        saved.setCondition(dto.getCondition());
        saved.setRegion(dto.getRegion());
        saved.setCity(dto.getCity());
        saved.setDistrict(dto.getDistrict());
        saved.setStreet(dto.getStreet());
        saved.setHouseNumber(dto.getHouseNumber());
        saved.setLongitude(dto.getLongitude());
        saved.setLatitude(dto.getLatitude());
        saved.setPrice(dto.getPrice());
        saved.setYearOfCommissioning(dto.getYearOfCommissioning());
        saved.setHeating(dto.getHeating());
        saved.setTelephone(dto.getTelephone());
        saved.setInternet(dto.getInternet());
        saved.setBathroomType(dto.getBathroomType());
        saved.setGas(dto.getGas());
        saved.setBalcony(dto.getBalcony());
        saved.setFurniture(dto.getFurniture());
        saved.setAirConditioner(dto.getAirConditioner());
        saved.setAnnouncementText(dto.getAnnouncementText());
        saved.setCreatedAt(LocalDateTime.now());
        saved.setUpdatedAt(LocalDateTime.now());

        when(propertyRepository.save(any(Property.class))).thenReturn(saved);

        // act
        PropertyResponseDTO result = propertyService.createProperty(dto);

        // assert
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Bishkek", result.getCity());
        assertEquals(BigDecimal.valueOf(1200.0), result.getPrice());
        assertFalse(result.getBooked());
    }

    @Test
    void updateProperty_whenOwner_shouldUpdateAndReturnDTO() {
        // arrange
        PropertyRequestDTO dto = buildFullDto();
        dto.setCity("NewCity");
        dto.setPrice(BigDecimal.valueOf(1500.0));

        User owner = new User();
        owner.setId(42L);
        owner.setUsername("alice");

        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("alice");
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(owner));

        Property existing = new Property();
        existing.setId(200L);
        existing.setOwner(owner);
        existing.setCity("OldCity");
        existing.setPrice(BigDecimal.valueOf(1000.0));
        when(propertyRepository.findById(200L)).thenReturn(Optional.of(existing));
        when(propertyRepository.save(existing)).thenReturn(existing);

        // act
        PropertyResponseDTO result = propertyService.updateProperty(200L, dto);

        // assert
        assertEquals("NewCity", result.getCity());
        assertEquals(BigDecimal.valueOf(1500.0), result.getPrice());

    }

    @Test
    void updateProperty_whenNotOwner_shouldThrowSecurityException() {
        // arrange
        User owner = new User();
        owner.setId(42L);

        Property existing = new Property();
        existing.setId(300L);
        existing.setOwner(owner);
        when(propertyRepository.findById(300L)).thenReturn(Optional.of(existing));

        User bob = new User();
        bob.setId(43L);
        bob.setUsername("bob");
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("bob");
        SecurityContextHolder.getContext().setAuthentication(auth);
        when(userRepository.findByUsername("bob")).thenReturn(Optional.of(bob));

        PropertyRequestDTO dto = buildFullDto();

        // act & assert
        assertThrows(SecurityException.class,
                () -> propertyService.updateProperty(300L, dto)
        );
    }

    @Test
    void getProperties_shouldFilterCorrectly() {
        // arrange
        Property p1 = new Property(); p1.setBooked(false); p1.setRegion("A"); p1.setCity("X"); p1.setNumberOfRooms(2);
        Property p2 = new Property(); p2.setBooked(true);  p2.setRegion("A"); p2.setCity("X"); p2.setNumberOfRooms(2);
        Property p3 = new Property(); p3.setBooked(false); p3.setRegion("B"); p3.setCity("Y"); p3.setNumberOfRooms(3);
        when(propertyRepository.findAll()).thenReturn(List.of(p1, p2, p3));

        // act
        List<PropertyResponseDTO> result = propertyService.getProperties("A", "X", 2);

        // assert
        assertEquals(1, result.size());
        assertEquals("A", result.get(0).getRegion());
        assertEquals("X", result.get(0).getCity());
        assertEquals(2, result.get(0).getNumberOfRooms());
    }

    @Test
    void deleteProperty_whenExists_shouldReturnTrue() {
        when(propertyRepository.existsById(5L)).thenReturn(true);

        boolean result = propertyService.deleteProperty(5L);

        assertTrue(result);
        verify(propertyRepository).deleteById(5L);
    }

    @Test
    void deleteProperty_whenNotExists_shouldReturnFalse() {
        when(propertyRepository.existsById(6L)).thenReturn(false);

        boolean result = propertyService.deleteProperty(6L);

        assertFalse(result);
        verify(propertyRepository, never()).deleteById(anyLong());
    }

    @Test
    void getAllPropertiesByUserId_shouldReturnDTOs() {
        Property p = new Property();
        p.setId(7L);
        User user = new User(); user.setId(7L);
        p.setOwner(user);
        when(propertyRepository.findByOwnerId(7L)).thenReturn(List.of(p));

        List<PropertyResponseDTO> result = propertyService.getAllPropertiesByUserId(7L);

        assertEquals(1, result.size());
        assertEquals(7L, result.get(0).getId());
    }
}
