package fr.formation.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOUtilisateur;
import fr.formation.model.Utilisateur;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IDAOUtilisateur dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Version 1
        // Optional<Utilisateur> optUtilisateur = this.dao.findByUsername(username);

        // if (optUtilisateur.isEmpty()) {
        //     throw new UsernameNotFoundException("Username not found");
        // }

        // Utilisateur utilisateur = optUtilisateur.get();

        // return User
        //     .withUsername(username)
        //     .password(utilisateur.getPassword())
        //     .roles(utilisateur.isAdmin() ? "ADMIN" : "USER")
        //     .build()
        // ;

        // Version 2
        // Utilisateur utilisateur = this.dao
        //     .findByUsername(username)
        //     .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        // return User
        //     .withUsername(username)
        //     .password(utilisateur.getPassword())
        //     .roles(utilisateur.isAdmin() ? "ADMIN" : "USER")
        //     .build()
        // ;

        // Version 3

        return this.dao
            // Recherche de l'utilisateur par son username
            .findByUsername(username)

            // Transformation de Utilisateur en UserDetails
            .map(utilisateur -> User
                .withUsername(username)
                .password(utilisateur.getPassword())
                .roles(utilisateur.isAdmin() ? "ADMIN" : "USER")
                .build()
            )

            // Si l'utilisateur n'existe pas, on jète l'exception UsernameNotFoundException
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"))
        ;
    }
}
