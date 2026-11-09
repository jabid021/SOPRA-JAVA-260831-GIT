package fr.bibliotek.api.response;

import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.model.Auteur;

public class AuteurResponse {
    private String id;
    private String nom;
    private String prenom;
    private NationaliteEnum nationalite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NationaliteEnum getNationalite() {
        return nationalite;
    }

    public void setNationalite(NationaliteEnum nationalite) {
        this.nationalite = nationalite;
    }

    public static AuteurResponse convert(Auteur auteur) {
        AuteurResponse response = new AuteurResponse();

        response.setId(auteur.getId());
        response.setNom(auteur.getNom());
        response.setPrenom(auteur.getPrenom());
        response.setNationalite(auteur.getNationalite());

        return response;
    }
}
