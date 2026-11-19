package fr.formation.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;
import fr.formation.response.ProduitResponse;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/produit")
public class ProduitResource {
    private List<Produit> produits = new ArrayList<>();
    private int lastId = 2;

    public ProduitResource() {
        this.produits.add(new Produit(1, "Parachute", new BigDecimal(("9850"))));
        this.produits.add(new Produit(2, "Casque de moto", new BigDecimal(("599"))));
    }

    @GET
    public List<ProduitResponse> findAll() {
        return this.produits
            .stream()
            .map(ProduitResponse::convert)
            .toList()
        ;
    }

    @POST
    public Response create(ProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        produit.setId(++this.lastId);

        this.produits.add(produit);

        return Response
            .status(Status.CREATED)
            .entity(
                Map.of("id", this.lastId)
            )
            .build()
        ;
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, ProduitRequest request) {
        Produit produit = null;

        // Exemple #1
        // for (Produit p : this.produits) {
        //     if (p.getId() == id) {
        //         produit = p;
        //         break;
        //     }
        // }

        // if (produit == null) {
        //     return Response.status(Status.NOT_FOUND).build();
        // }

        // Exemple #2
        produit = this.produits.stream().filter(p -> p.getId() == id).findFirst().orElseThrow();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        return Response.ok(Map.of("id", id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Integer id) {
        this.produits.removeIf(p -> p.getId() == id);
    }
}
