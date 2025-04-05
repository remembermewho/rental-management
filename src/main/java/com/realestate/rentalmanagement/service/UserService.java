package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.payload.request.UserRequestDTO;
import com.realestate.rentalmanagement.payload.response.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // Регистрация нового пользователя
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    // Получение информации о пользователе по ID
    UserResponseDTO getUserById(Long id);

    // Обновление данных пользователя
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    // Удаление пользователя по ID
    boolean deleteUser(Long id);
}
