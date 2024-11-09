package org.example.tp3springboot.controller;

import org.example.tp3springboot.entity.Utilisateur;
import org.example.tp3springboot.entity.UtilisateurImage;
import org.example.tp3springboot.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @PostMapping
    public Utilisateur creerUtilisateur(@RequestParam String nom, @RequestParam String email, @RequestParam String nomRole) {
        return utilisateurService.creerUtilisateur(nom, email, nomRole);
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.findById(id).orElse(null);
    }

    @PutMapping("/{utilisateurId}/role/{roleId}")
    public Utilisateur assignerRole(@PathVariable Long utilisateurId, @PathVariable Long roleId) {
        return utilisateurService.assignerRole(utilisateurId, roleId);
    }

    @PostMapping("/{utilisateurId}/image")
    public UtilisateurImage ajouterImage(@PathVariable Long utilisateurId, @RequestParam String nomImage, @RequestParam String cheminImage) {
        return utilisateurService.ajouterImageAUtilisateur(utilisateurId, nomImage, cheminImage);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteById(id);
    }

    @DeleteMapping("/role/{roleId}")
    public void deleteRole(@PathVariable Long roleId) {
        utilisateurService.deleteRoleById(roleId);
    }

    @DeleteMapping("/{utilisateurId}/image/{imageId}")
    public void deleteImage(@PathVariable Long utilisateurId, @PathVariable Long imageId) {
        utilisateurService.deleteImage(utilisateurId, imageId);
    }
}