package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
        // Configuration des autorisations d'accès
        http.authorizeHttpRequests(authorize -> {
            // !!! L'ordre a une importance ici
            // Il faut faire de la règle la plus spécifique à la règle la plus générale

            // authorize.requestMatchers("/api/hello").permitAll();
            // authorize.requestMatchers("/api/hello").authenticated();

            // /api/hello UNIQUEMENT pour les roles ADMIN
            authorize.requestMatchers(HttpMethod.POST, "/api/hello").hasAnyRole("ADMIN");
            authorize.requestMatchers("/api/hello/create").hasAnyRole("ADMIN");

            // /api/hello/quelchose/nimportequoi UNIQUEMENT pour les roles ADMIN et USER
            authorize.requestMatchers("/api/hello/**").hasAnyRole("ADMIN", "USER");

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
