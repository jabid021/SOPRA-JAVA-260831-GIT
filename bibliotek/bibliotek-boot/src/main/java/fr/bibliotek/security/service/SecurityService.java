package fr.bibliotek.security.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import fr.bibliotek.api.request.AuthRequest;
import fr.bibliotek.api.response.AuthResponse;

@Service
public class SecurityService {
    private final static Logger log = LoggerFactory.getLogger(SecurityService.class);
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public SecurityService(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    public AuthResponse auth(AuthRequest request) {
        try {
            log.debug("Tentative d'authentification ...");

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("Authentification validée !");

            Instant now = Instant.now();

            String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "))
            ;

            // Payload - Contenu du jeton JWT
            JwtClaimsSet payload = JwtClaimsSet.builder()
                .subject(request.username())
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("scope", scope)
                .build()
            ;

            // Header - En-tête du jeton JWT
            JwsHeader header = JwsHeader
                .with(MacAlgorithm.HS256)
                .build()
            ;

            // Génération du jeton JWT avec le JwtEncoder
            String token = this.jwtEncoder
                .encode(JwtEncoderParameters.from(header, payload))
                .getTokenValue()
            ;

            return new AuthResponse(true, token);


        }

        catch (BadCredentialsException ex) {
            log.error("Impossible de s'authentifier : mauvais identifiants.");
        }

        catch (Exception ex) {
            log.error("Impossible de s'authentifier : erreur ({}).", ex.getClass().getSimpleName());
        }

        return new AuthResponse(false, "");
    }
}
