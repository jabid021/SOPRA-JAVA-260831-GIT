package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    // Ici on va gérer nos propres utilisateurs en mémoire
    @Bean
    UserDetailsService inMemory() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
            User.withUsername("admin")
                .password("{noop}123456$")
                .roles("ADMIN", "plusieurs")
                .build()
        );

        manager.createUser(
            User.withUsername("user")
                .password("{noop}123456$")
                .roles("USER")
                .build()
        );

        return manager;
    }
}
