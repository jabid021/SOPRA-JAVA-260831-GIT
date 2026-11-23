package fr.formation.servicecommentaire.clientrest;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BearerTokenFilter implements ClientRequestFilter {
    @Inject
    private JsonWebToken jwt;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (jwt != null && jwt.getRawToken() != null) {
            requestContext.getHeaders().add("Authorization", "Bearer " + jwt.getRawToken());
        }
    }
}
