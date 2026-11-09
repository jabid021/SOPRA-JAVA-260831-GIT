package fr.bibliotek.api.response;

import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.model.Editeur;

public class EditeurResponse {
    private String id;
    private String nom;
    private NationaliteEnum pays;

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

    public NationaliteEnum getPays() {
        return pays;
    }

    public void setPays(NationaliteEnum nationalite) {
        this.pays = nationalite;
    }

    public static EditeurResponse convert(Editeur editeur) {
        EditeurResponse response = new EditeurResponse();

        response.setId(editeur.getId());
        response.setNom(editeur.getNom());
        response.setPays(editeur.getPays());

        return response;
    }
}
