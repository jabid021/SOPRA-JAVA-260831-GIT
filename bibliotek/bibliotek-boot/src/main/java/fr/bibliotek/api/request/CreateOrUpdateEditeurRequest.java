package fr.bibliotek.api.request;

import fr.bibliotek.enumerator.NationaliteEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateOrUpdateEditeurRequest {
    @NotBlank
    private String nom;

    @NotNull
    private NationaliteEnum pays;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NationaliteEnum getPays() {
        return pays;
    }

    public void setPays(NationaliteEnum pays) {
        this.pays = pays;
    }
}
