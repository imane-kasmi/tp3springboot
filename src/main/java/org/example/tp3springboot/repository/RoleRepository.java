package org.example.tp3springboot.repository;

import org.example.tp3springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNom(String nom);
}
