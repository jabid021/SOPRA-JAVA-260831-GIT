package fr.bibliotek.api.response;

import fr.bibliotek.model.Collection;

public class CollectionResponse {
    private String id;
    private String nom;

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

    public static CollectionResponse convert(Collection collection) {
        CollectionResponse response = new CollectionResponse();

        response.setId(collection.getId());
        response.setNom(collection.getNom());

        return response;
    }
}
