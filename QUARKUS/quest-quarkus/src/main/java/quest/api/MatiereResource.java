package quest.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import quest.model.Matiere;
import quest.repo.MatiereRepository;
import quest.request.CreateOrUpdateMatiereRequest;
import quest.response.MatiereResponse;

@Path("/api/matiere")
public class MatiereResource {
    private static Logger log = LoggerFactory.getLogger(MatiereResource.class);
    private final MatiereRepository repository;

    public MatiereResource(MatiereRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<MatiereResponse> findAll() {
        log.debug("Liste des matières ...");

        return this.repository.findAll().stream().map(MatiereResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public MatiereResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche de la matière {} ...", id);

        return MatiereResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @GET
    @Path("/by-libelle/{libelle}")
    public MatiereResponse findByLibelle(@PathParam("libelle") String libelle) {
        log.debug("Recherche de la matière par libellé {} ...", libelle);

        return MatiereResponse.convert(this.repository.findByLibelle(libelle).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public Response create(@Valid CreateOrUpdateMatiereRequest request) {
        log.debug("Création d'une nouvelle matière ...");

        Matiere matiere = new Matiere();

        matiere.setLibelle(request.libelle());

        this.repository.persist(matiere);

        log.debug("Matière créée !");

        return Response.status(Response.Status.CREATED)
            .entity(Map.of("id", matiere.getId()))
            .build()
        ;
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid CreateOrUpdateMatiereRequest request) {
        log.debug("Modification de la matière {} ...", id);

        Matiere matiere = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        matiere.setLibelle(request.libelle());

        this.repository.persist(matiere);

        log.debug("Matière modifiée !");

        return Response.ok(Map.of("id", matiere.getId())).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression de la matière {} ...", id);

        this.repository.deleteById(id);

        log.debug("Matière supprimée !");

        return Response.noContent().build();
    }
}
