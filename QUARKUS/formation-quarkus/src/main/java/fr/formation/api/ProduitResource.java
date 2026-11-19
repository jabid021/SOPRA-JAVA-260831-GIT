package fr.formation.api;

import java.util.List;
import java.util.Map;

import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;
import fr.formation.request.ProduitRequest;
import fr.formation.response.ProduitResponse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/produit")
public class ProduitResource {
    private final ProduitRepository repository;

    public ProduitResource(ProduitRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<ProduitResponse> findAll() {
        return this.repository.findAll()
            .stream()
            .map(ProduitResponse::convert)
            .toList()
        ;
    }

    @POST
    @Transactional
    public Response create(ProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return Response
            .status(Status.CREATED)
            .entity(
                Map.of("id", produit.getId())
            )
            .build()
        ;
    }

    @POST
    @Path("/v2")
    @Transactional
    public ProduitResponse createV2(ProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return ProduitResponse.convert(produit);
    }

    @POST
    @Path("/v3")
    @Transactional
    public Response createV3(ProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return Response
            .status(Status.CREATED)
            .entity(ProduitResponse.convert(produit))
            .build()
        ;
    }

    @POST
    @Path("/v4")
    @Transactional
    public Response createV4(ProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return Response
            .status(Status.CREATED)
            .entity(new ProduitIdResponse(produit.getId()))
            .build()
        ;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, ProduitRequest request) {
        Produit produit = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return Response.ok(Map.of("id", id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteById(@PathParam("id") Integer id) {
        this.repository.deleteById(id);
    }


    // Map.of("id", produit.getId())

    // -> EQUIVAUT A

    public static record ProduitIdResponse(Integer id) {

    }
}
