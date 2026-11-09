package fr.bibliotek.api.request;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateCollectionRequest {
    @NotBlank
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
