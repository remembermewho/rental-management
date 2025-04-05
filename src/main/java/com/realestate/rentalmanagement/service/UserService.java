package com.realestate.rentalmanagement.service;

import com.realestate.rentalmanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    Optional<User> findByUsername(String username);
}
