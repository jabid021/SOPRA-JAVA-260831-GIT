package quest.restcontroller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quest.dto.response.AuthResponse;
import quest.request.dto.AuthRequest;

@RestController
@RequestMapping("/api/user")
public class UtilisateurRestController {
    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;

    public UtilisateurRestController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        // On prépare l'authentification utilisateur
        Authentication auth = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        // On demande à Spring Security de vérifier l'authentification
        auth = this.authenticationManager.authenticate(auth);

        // Si tout est OK, "auth" fera référence à l'utilisateur connecté !
        // Si ça n'a pas fonctionné, Erreur 401, la suite ne s'exécutera pas

        Instant now = Instant.now();

        String scope = auth.getAuthorities()
            .stream() // Transforme la liste en Stream Java
            // .map(a -> a.getAuthority()) // Permet de transformer le GrantedAuthority en autre chose : en String
            .map(GrantedAuthority::getAuthority) // Permet de transformer le GrantedAuthority en autre chose : en String

            // Recollecter les String en une seule String, séparateur " " (espace)
            .collect(Collectors.joining(" "))
        ;

        // Payload - Contenu du jeton JWT
        JwtClaimsSet payload = JwtClaimsSet.builder()
            .subject(request.username()) // Le nom d'utilisateur
            .issuedAt(now) // La date / heure de la création du jeton
            .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Date de validité = 1 heure

            // Liste des rôles / autorisations / etc.
            .claim("scope", scope)

            .build()
        ;

        // Header - En-tête du jeton JWT
        JwsHeader header = JwsHeader
            .with(MacAlgorithm.HS256) // HmacSHA256
            .build()
        ;

        // Génération du jeton JWT avec le JwtEncoder
        String token = this.jwtEncoder
            .encode(JwtEncoderParameters.from(header, payload))
            .getTokenValue()
        ;

        return new AuthResponse(true, token);
    }
}
