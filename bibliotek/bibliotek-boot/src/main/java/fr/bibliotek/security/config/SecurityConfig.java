package fr.bibliotek.security.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private static final String SECRET_KEY = "3f79ae1cb1016bebe4c00a841f8bf0c90b03c2ad896cad9086ae6792ce109549";

    private SecretKey secretKey() {
        // On transforme le secret en tableau d'octets
        // On retourne la clé secrète au format Hmac SHA 256
        return new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    // Ce JwtEncoder permettra de générer des jetons JWT, signés avec la clé privée
    @Bean
    JwtEncoder jwtEncoder() {
        SecretKey secretKey = this.secretKey();

        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
    }

    // Ce JwtDecoder permettra de valider les jetons reçus dans les requêtes HTTP, utilisé par le Filter qui se chargera de valider le jeton
    @Bean
    JwtDecoder jwtDecoder() {
        SecretKey secretKey = this.secretKey();

        return NimbusJwtDecoder
            .withSecretKey(secretKey)
            .macAlgorithm(MacAlgorithm.HS256) // HmacSHA256
            .build()
        ;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
        http.authorizeHttpRequests(authorization -> {
            authorization.requestMatchers("/auth", "/utilisateur/inscription").permitAll();
            authorization.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll();
            authorization.requestMatchers("/**").authenticated();
        });

        // Remet le HTTP Basic
        http.httpBasic(Customizer.withDefaults());

        // Activer le filtre de validation du jeton JWT
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        // On désactive les Sessions HTTP
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Désactiver la protection CSRF uniquement pour les /quelquechose
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/**"));

        // Ici, on utilisera le CorsConfigurationSource défini plus bas
        http.cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter granted = new JwtGrantedAuthoritiesConverter();
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        // Par défaut, le préfix est "SCOPE_"
        granted.setAuthorityPrefix("");

        converter.setJwtGrantedAuthoritiesConverter(granted);

        return converter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*"));

        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
