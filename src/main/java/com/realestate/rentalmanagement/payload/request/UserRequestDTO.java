package com.realestate.rentalmanagement.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "Username обязателен")
    private String username;

    @NotBlank(message = "Password обязателен")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotNull(message = "Роль обязательна")
    private String role;

    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    public @NotBlank(message = "Username обязателен") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username обязателен") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password обязателен") @Size(min = 6, message = "Пароль должен содержать минимум 6 символов") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password обязателен") @Size(min = 6, message = "Пароль должен содержать минимум 6 символов") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Email обязателен") @Email(message = "Некорректный формат email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email обязателен") @Email(message = "Некорректный формат email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Роль обязательна") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Роль обязательна") String role) {
        this.role = role;
    }

    public @NotBlank(message = "Имя обязательно") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "Имя обязательно") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Фамилия обязательна") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Фамилия обязательна") String lastName) {
        this.lastName = lastName;
    }
}
