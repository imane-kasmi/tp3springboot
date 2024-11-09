package org.example.tp3springboot.repository;

import org.example.tp3springboot.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
    List<Utilisateur> findByRoleNom(String nomRole);
}
