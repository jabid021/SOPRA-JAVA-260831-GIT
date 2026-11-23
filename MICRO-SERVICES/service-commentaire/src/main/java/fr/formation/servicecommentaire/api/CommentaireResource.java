package fr.formation.servicecommentaire.api;

import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.servicecommentaire.api.dto.request.CreateCommentaireRequest;
import fr.formation.servicecommentaire.clientrest.ProduitClientRest;
import fr.formation.servicecommentaire.model.Commentaire;
import fr.formation.servicecommentaire.repo.CommentaireRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/api/commentaire")
public class CommentaireResource {
    private static Logger log = LoggerFactory.getLogger(CommentaireResource.class);

    @RestClient
    @Inject
    private ProduitClientRest produitClientRest;

    @Inject
    private CommentaireRepository repository;

    @GET
    @Path("/by-produit-id/note/{produitId}")
    @Produces("application/json")
    public int findNoteByProduitId(@PathParam("produitId") String produitId) {
        log.debug("Recherche de la note pour le produit {} ...", produitId);

        return this.repository.findNoteByProduitId(produitId);
    }

    @Transactional
    @POST
    public Response create(@Valid CreateCommentaireRequest request) {
        log.debug("Création d'un nouveau commentaire ...");
        log.debug("Vérification : est-ce que le produit {} est notable ?", request.produitId());

        try {
            boolean isNotable = this.produitClientRest.isNotable(request.produitId());

            if (!isNotable) {
                log.debug("Le produit {} n'est pas notable!", request.produitId());

                return Response.status(Response.Status.FORBIDDEN)
                    .entity(Map.of("notable", false))
                    .build()
                ;
            }
        }

        catch (WebApplicationException ex) {
            if (ex.getResponse().getStatus() == 404) {
                log.error("Une erreur est survenue pendant la vérification du produit {} notable: {}", request.produitId(), ex.getMessage());

                return Response.status(Response.Status.NOT_FOUND).build();
            }

            else {
                log.error("Une erreur est survenue pendant la vérification du produit {} notable: {}", request.produitId(), ex.getMessage());

                return Response.status(Response.Status.BAD_REQUEST)
                    .entity(
                        Map.of(
                            "error", ex.getMessage(),
                            "status", ex.getResponse().getStatus()
                        )
                    )
                    .build()
                ;
            }
        }

        Commentaire commentaire = new Commentaire();

        commentaire.setNote(request.note());
        commentaire.setProduitId(request.produitId());

        this.repository.persist(commentaire);

        log.debug("Commentaire {} créé !", commentaire.getId());

        return Response.status(Response.Status.CREATED)
            .entity(commentaire.getId())
            .build()
        ;
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public String deleteById(@PathParam("id") String id) {
        log.debug("Suppression du commentaire {} ...", id);

        this.repository.deleteById(id);

        log.debug("Commentaire {} supprimé !", id);

        return id;
    }

    @Transactional
    @DELETE
    @Path("/by-produit-id/{produitId}")
    public String deleteAllByProduitId(@PathParam("produitId") String produitId) {
        log.debug("Suppression des commentaires du produit {} ...", produitId);

        this.repository.deleteAllByProduitId(produitId);

        log.debug("Commentaires du produit {} supprimé !", produitId);

        return produitId;
    }
}
