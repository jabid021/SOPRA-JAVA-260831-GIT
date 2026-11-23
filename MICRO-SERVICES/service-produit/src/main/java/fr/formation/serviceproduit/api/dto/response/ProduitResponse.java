package fr.formation.serviceproduit.api.dto.response;

import java.math.BigDecimal;

import fr.formation.serviceproduit.model.Produit;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitResponse {
    private String id;
    private String libelle;
    private BigDecimal prix;
    private int note;

    public static ProduitResponse convert(Produit produit) {
        ProduitResponse resp = new ProduitResponse();

        resp.setId(produit.getId());
        resp.setLibelle(produit.getLibelle());
        resp.setPrix(produit.getPrix());

        return resp;
    }
}
