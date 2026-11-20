package quest.api;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.jpa.runtime.JpaIdentityProvider;
import io.smallrye.jwt.build.Jwt;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import quest.request.AuthRequest;
import quest.response.AuthResponse;

@Path("/api/user/auth")
public class AuthResource {
    private final JpaIdentityProvider jpaIdentityProvider;
    private final EntityManager entityManager;

    public AuthResource(JpaIdentityProvider jpaIdentityProvider, EntityManager entityManager) {
        this.jpaIdentityProvider = jpaIdentityProvider;
        this.entityManager = entityManager;
    }

    @POST
    public AuthResponse auth(AuthRequest request) {
        UsernamePasswordAuthenticationRequest authRequest = new UsernamePasswordAuthenticationRequest(
            request.username(), // Username
            new PasswordCredential(request.password().toCharArray()) // Password
        );

        // Authentification de l'utilisateur
        SecurityIdentity identity = this.jpaIdentityProvider.authenticate(this.entityManager, authRequest);

        // Une fois qu'on a l'utilisateur authentifié, on peut générer le jeton JWT
        String jwt = Jwt.issuer("quest-quarkus-issuer")
            .upn(request.username()) // User Principal Name, le nom d'utilisateur
            .groups(identity.getRoles()) // Liste des rôles de l'utilisateur connecté, avec @Roles de @UserDefinition
            .sign()
        ;

        return new AuthResponse(true, jwt);
    }
}
