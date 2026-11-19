package fr.formation.response;

import java.math.BigDecimal;

import fr.formation.model.Produit;

public record ProduitResponse(int id, String libelle, BigDecimal prix) {
    public static ProduitResponse convert(Produit produit) {
        return new ProduitResponse(
            produit.getId(),
            produit.getLibelle(),
            produit.getPrix()
        );
    }
}
