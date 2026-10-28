package fr.formation.config;

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
        Utilisateur utilisateur = this.dao.findByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return User
            .withUsername(username)
            .password(utilisateur.getPassword())
            .roles(utilisateur.isAdmin() ? "ADMIN" : "USER")
            .build()
        ;
    }
}
