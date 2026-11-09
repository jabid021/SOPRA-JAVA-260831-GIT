package fr.bibliotek.api.request;

import fr.bibliotek.enumerator.NationaliteEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateOrUpdateAuteurRequest {
    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotNull
    private NationaliteEnum nationalite;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public NationaliteEnum getNationalite() {
        return nationalite;
    }

    public void setNationalite(NationaliteEnum nationalite) {
        this.nationalite = nationalite;
    }
}
