package com.realestate.rentalmanagement.repository;

import com.realestate.rentalmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
