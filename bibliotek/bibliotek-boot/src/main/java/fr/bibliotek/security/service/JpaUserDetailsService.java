package fr.bibliotek.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.bibliotek.repo.UtilisateurRepository;


@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository repository;

    public JpaUserDetailsService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository
            // Recherche de l'utilisateur par son username
            .findByUsername(username)

            // Transformation de Utilisateur en UserDetails
            .map(personne -> User
                .withUsername(username)
                .password(personne.getPassword())
                .roles("USER")
                .build()
            )

            // Si l'utilisateur n'existe pas, on jète l'exception UsernameNotFoundException
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"))
        ;
    }
}
