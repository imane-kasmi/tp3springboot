package org.example.tp3springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String nom;
    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email ne doit pas être vide")
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private UtilisateurImage utilisateurImage;
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UtilisateurImage getUtilisateurImage() {
        return utilisateurImage;
    }

    public void setUtilisateurImage(UtilisateurImage utilisateurImage) {
        this.utilisateurImage = utilisateurImage;
    }
}
