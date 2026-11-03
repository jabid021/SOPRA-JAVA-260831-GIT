package quest.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        // Configuration des autorisations d'accès
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.POST, "/api/user/auth").permitAll();
            authorize.requestMatchers("/api/**").authenticated();
        });

        // Remet le HTTP Basic
        http.httpBasic(Customizer.withDefaults());

        // Activer le filtre de validation du jeton JWT
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        // Désactiver la protection CSRF uniquement pour les /api/quelquechose
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

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

        // corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
        corsConfiguration.setAllowedMethods(List.of("*"));

        // corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));

        // Pour toutes les ressources, on applique la politique CORS définie plus haut
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // On intègre l'AuthenticationManager dans le contexte de Spring pour pouvoir l'utiliser directement
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }
}
