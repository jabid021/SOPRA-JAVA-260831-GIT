package fr.formation.request;

import java.math.BigDecimal;

import jakarta.ws.rs.QueryParam;

public class ProduitBeanParamRequest {
    @QueryParam("libelle")
    private String libelle;

    @QueryParam("prix")
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
