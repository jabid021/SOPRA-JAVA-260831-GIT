package fr.bibliotek.api.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.bibliotek.model.Avis;

public class AvisResponse {
    private String id;
    private int note;
    private String commentaire;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private String livreId;
    private String livreNom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLivreId() {
        return livreId;
    }

    public void setLivreId(String livreId) {
        this.livreId = livreId;
    }

    public String getLivreNom() {
        return livreNom;
    }

    public void setLivreNom(String livreNom) {
        this.livreNom = livreNom;
    }

    public static AvisResponse convert(Avis avis) {
        AvisResponse response = new AvisResponse();

        response.setId(avis.getId());
        response.setNote(avis.getNote());
        response.setCommentaire(avis.getCommentaire());
        response.setDate(avis.getDate());

        if (avis.getLivre() != null) {
            response.setLivreId(avis.getLivre().getId());
            response.setLivreNom(avis.getLivre().getNom());
        }

        return response;
    }
}
