package fr.formation;

import fr.formation.musique.Guitariste;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @Inject
    private Guitariste guitariste;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        this.guitariste.jouer();

        return "Hello from Quarkus REST (2)";
    }
}
