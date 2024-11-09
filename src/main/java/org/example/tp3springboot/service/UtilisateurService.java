package org.example.tp3springboot.service;

import org.example.tp3springboot.entity.Role;
import org.example.tp3springboot.entity.Utilisateur;
import org.example.tp3springboot.entity.UtilisateurImage;
import org.example.tp3springboot.repository.RoleRepository;
import org.example.tp3springboot.repository.UtilisateurImageRepository;
import org.example.tp3springboot.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurImageRepository utilisateurImageRepository;

    // Méthode pour récupérer tous les utilisateurs
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    // Méthode pour récupérer un utilisateur par ID
    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Méthode pour créer un nouvel utilisateur
    public Utilisateur creerUtilisateur(String nom, String email, String nomRole) {
        Role role = roleRepository.findByNom(nomRole);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setRole(role);
        return utilisateurRepository.save(utilisateur);
    }

    // Méthode pour assigner un rôle à un utilisateur
    public Utilisateur assignerRole(Long utilisateurId, Long roleId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role introuvable"));
        utilisateur.setRole(role);
        return utilisateurRepository.save(utilisateur);
    }

    // Méthode pour ajouter une image à un utilisateur
    public UtilisateurImage ajouterImageAUtilisateur(Long utilisateurId, String nomImage, String cheminImage) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        UtilisateurImage image = new UtilisateurImage();
        image.setNomImage(nomImage);
        image.setCheminImage(cheminImage);
        image.setUtilisateur(utilisateur);
        return utilisateurImageRepository.save(image);
    }

    // Méthode pour supprimer un utilisateur par ID
    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    // Méthode pour supprimer un rôle par ID
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    // Méthode pour supprimer une image par ID pour un utilisateur spécifique
    public void deleteImage(Long utilisateurId, Long imageId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        UtilisateurImage image = utilisateurImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image introuvable"));
        if (image.getUtilisateur().equals(utilisateur)) {
            utilisateurImageRepository.delete(image);
        } else {
            throw new RuntimeException("L'image ne correspond pas à cet utilisateur");
        }
    }
}
