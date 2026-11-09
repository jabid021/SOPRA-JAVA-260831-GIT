package fr.bibliotek.api.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.bibliotek.model.Livre;

public class LivreResponse {
    private String id;
    private String nom;
    private String resume;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publication;

    private String auteurId;
    private String auteurNom;

    private String editeurId;
    private String editeurNom;

    private String collectionId;
    private String collectionNom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public String getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(String auteurId) {
        this.auteurId = auteurId;
    }

    public String getAuteurNom() {
        return auteurNom;
    }

    public void setAuteurNom(String auteurNom) {
        this.auteurNom = auteurNom;
    }

    public String getEditeurId() {
        return editeurId;
    }

    public void setEditeurId(String editeurId) {
        this.editeurId = editeurId;
    }

    public String getEditeurNom() {
        return editeurNom;
    }

    public void setEditeurNom(String editeurNom) {
        this.editeurNom = editeurNom;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionNom() {
        return collectionNom;
    }

    public void setCollectionNom(String collectionNom) {
        this.collectionNom = collectionNom;
    }

    public static LivreResponse convert(Livre livre) {
        LivreResponse response = new LivreResponse();

        response.setId(livre.getId());
        response.setNom(livre.getNom());
        response.setResume(livre.getResume());
        response.setPublication(livre.getPublication());

        if (livre.getAuteur() != null) {
            response.setAuteurId(livre.getAuteur().getId());
            response.setAuteurNom(livre.getAuteur().getNom());
        }

        if (livre.getEditeur() != null) {
            response.setEditeurId(livre.getEditeur().getId());
            response.setEditeurNom(livre.getEditeur().getNom());
        }

        if (livre.getCollection() != null) {
            response.setCollectionId(livre.getCollection().getId());
            response.setCollectionNom(livre.getCollection().getNom());
        }

        return response;
    }
}
