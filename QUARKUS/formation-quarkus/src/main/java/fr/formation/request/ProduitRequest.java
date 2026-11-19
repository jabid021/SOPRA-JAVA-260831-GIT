package fr.formation.request;

import java.math.BigDecimal;

public class ProduitRequest {
    private String libelle;
    private BigDecimal prix;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
}
