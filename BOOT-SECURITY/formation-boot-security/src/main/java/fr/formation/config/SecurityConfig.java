package fr.formation.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
// Activer les annotations @PreAuthorize / @PostAuthorize
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
            // !!! L'ordre a une importance ici
            // Il faut faire de la règle la plus spécifique à la règle la plus générale

            // authorize.requestMatchers("/api/hello").permitAll();
            // authorize.requestMatchers("/api/hello").authenticated();

            authorize.requestMatchers(HttpMethod.POST, "/api/user/subscribe").permitAll();
            authorize.requestMatchers("/api/user/jwt").permitAll();

            // /api/hello UNIQUEMENT pour les roles ADMIN
            // authorize.requestMatchers(HttpMethod.POST, "/api/hello").hasAnyRole("ADMIN");
            // authorize.requestMatchers("/api/hello/create").hasAnyRole("ADMIN");

            // // /api/hello/quelchose/nimportequoi UNIQUEMENT pour les roles ADMIN et USER
            // authorize.requestMatchers("/api/hello/**").hasAnyRole("ADMIN", "USER");

            // authorize.requestMatchers("/api/auth/**").permitAll();

            // Tout le reste il faut être authentifié
            authorize.requestMatchers("/api/**").authenticated();
        });

        // Reconfiguration d'un formulaire de connexion
        http.formLogin(Customizer.withDefaults());

        // Remet le HTTP Basic
        http.httpBasic(Customizer.withDefaults());


        // Désactiver la protection CSRF uniquement pour les /api/quelquechose
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    // Ici on va gérer nos propres utilisateurs en mémoire
    // @Bean
    UserDetailsService inMemory() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
            User.withUsername("admin")
                // .password("123456$")
                .password("$2a$10$7LUTWk1B5nqyykF5vUPcF.QD4/8r77GU/BUgAzGUrGC5LY9Ypa7su")
                .roles("ADMIN", "plusieurs")
                .build()
        );

        manager.createUser(
            User.withUsername("user")
                // .password("123456$")
                .password("$2a$10$7LUTWk1B5nqyykF5vUPcF.QD4/8r77GU/BUgAzGUrGC5LY9Ypa7su")
                .roles("USER")
                .build()
        );

        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("123456$"));
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
