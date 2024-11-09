package org.example.tp3springboot.repository;

import org.example.tp3springboot.entity.UtilisateurImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurImageRepository extends JpaRepository<UtilisateurImage, Long> {
    UtilisateurImage findByUtilisateurId(Long utilisateurId);
}
