package fr.formation.servicesecurity.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import fr.formation.servicesecurity.repo.UtilisateurRepository;

@Configuration
public class SecurityConfig {
    @Value("${ajc.security.private-key}")
    private RSAPrivateKey privateKey;

    @Value("${ajc.security.public-key}")
    private RSAPublicKey publicKey;

    @Bean
    SecurityFilterChain filterChain(final HttpSecurity http) {
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    UserDetailsService jpaUserDetailsService(final UtilisateurRepository utilisateurRepository) {
        return username -> {
            return utilisateurRepository
                .findByUsername(username)
                .map(u -> User.builder()
                    .username(username)
                    .password(u.getPassword())
                    .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Mauvais accès"))
            ;
        };
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

    @Bean
    RSAKey rsaKey() {
        return new RSAKey.Builder(this.publicKey)
                .privateKey(this.privateKey)
                .keyID("ajc-key-demo")
                .build();
    }

    @Bean
    JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(rsaKey);

        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
