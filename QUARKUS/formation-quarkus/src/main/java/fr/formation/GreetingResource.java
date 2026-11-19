package fr.formation;

import java.util.Map;

import fr.formation.musique.Guitariste;
import fr.formation.request.ProduitBeanParamRequest;
import fr.formation.request.ProduitRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/hello")
public class GreetingResource {
    @Inject
    private Guitariste guitariste;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        this.guitariste.jouer();

        return "Hello from Quarkus REST (3)";
    }

    @GET
    @Path("/test")
    public String test() {
        return "Test";
    }

    @GET
    @Path("/guitariste")
    public Guitariste guitariste() {
        this.guitariste.setNom("The rocker");

        return this.guitariste;
    }

    @GET
    @Path("/query-param")
    public String queryParam(@QueryParam("libelle") String libelle) {
        return "Le nom du paramètre est " + libelle;
    }

    @GET
    @Path("/path-param/{libelle}")
    public String pathParam(@PathParam("libelle") String libelle) {
        return "Le nom du paramètre est " + libelle;
    }

    @GET
    @Path("/bean-param")
    public String beanParam(@BeanParam ProduitBeanParamRequest request) {
        return "Le nom du produit est " + request.getLibelle();
    }

    @POST
    public String post(ProduitRequest request) {
        return "Le nom du produit est " + request.getLibelle();
    }

    @POST
    @Path("/response")
    public Response postResponse(ProduitRequest request) {
        return Response
            .status(Status.CREATED)
            .entity(
                Map.of(
                    "message",
                    "Le nom du produit est " + request.getLibelle()
                )
            )
            .build()
        ;
    }
}
