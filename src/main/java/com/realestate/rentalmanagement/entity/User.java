package com.realestate.rentalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "account_number")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username обязателен")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password обязателен")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Роль обязательна")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean enabled;

    @NotBlank(message = "Имя обязательно")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Баланс обязателен") // Баланс
    @DecimalMin(value = "0.0", inclusive = true, message = "Баланс не может быть отрицательным")
    @Column(nullable = false)
    private BigDecimal balance;

    @NotBlank(message = "Лицевой счёт обязателен")
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Конструкторы, геттеры и сеттеры

    public User() {}

    public User(Long id, String username, String password, String email, Role role, boolean enabled, String firstName, String lastName, BigDecimal balance, String accountNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public @NotNull(message = "Роль обязательна") Role getRole() {
        return role;
    }

    public void setRole(@NotNull(message = "Роль обязательна") Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public @NotNull(message = "Баланс обязателен") @DecimalMin(value = "0.0", inclusive = true, message = "Баланс не может быть отрицательным") BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(@NotNull(message = "Баланс обязателен") @DecimalMin(value = "0.0", inclusive = true, message = "Баланс не может быть отрицательным") BigDecimal balance) {
        this.balance = balance;
    }

    public @NotBlank(message = "Лицевой счёт обязателен") String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotBlank(message = "Лицевой счёт обязателен") String accountNumber) {
        this.accountNumber = accountNumber;
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
}
