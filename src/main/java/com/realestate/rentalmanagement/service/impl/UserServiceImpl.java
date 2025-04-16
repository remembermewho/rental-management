package com.realestate.rentalmanagement.service.impl;

import com.realestate.rentalmanagement.entity.Role;
import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.payload.request.UserRequestDTO;
import com.realestate.rentalmanagement.payload.response.UserResponseDTO;
import com.realestate.rentalmanagement.repository.RoleRepository;
import com.realestate.rentalmanagement.repository.UserRepository;
import com.realestate.rentalmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод для маппинга сущности User в UserResponseDTO
    private UserResponseDTO mapToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().getName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBalance(user.getBalance());
        dto.setAccountNumber(user.getAccountNumber());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    // Метод для маппинга UserRequestDTO в сущность User
    private User mapToEntity(UserRequestDTO dto) {

        Role role = roleRepository.findByName(dto.getRole());
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(role);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }

    @Override
    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        // Маппинг DTO в сущность
        User user = mapToEntity(userRequestDTO);
        // Устанавливаем начальные значения
        user.setBalance(BigDecimal.ZERO);
        user.setAccountNumber("ACC" + UUID.randomUUID());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setEnabled(true);
        User createdUser = userRepository.save(user);
        return mapToDTO(createdUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        Role role = roleRepository.findByName(userRequestDTO.getRole());

        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(userRequestDTO.getUsername());
            existingUser.setEmail(userRequestDTO.getEmail());
            existingUser.setPassword(userRequestDTO.getPassword());
            existingUser.setRole(role);
            existingUser.setFirstName(userRequestDTO.getFirstName());
            existingUser.setLastName(userRequestDTO.getLastName());
            existingUser.setUpdatedAt(LocalDateTime.now());
            User updatedUser = userRepository.save(existingUser);
            return mapToDTO(updatedUser);
        }).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
