package quest.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import quest.dao.IDAOPersonne;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final IDAOPersonne dao;

    public JpaUserDetailsService(IDAOPersonne dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.dao
            // Recherche de l'utilisateur par son username
            .findByLogin(username)

            // Transformation de Utilisateur en UserDetails
            .map(personne -> User
                .withUsername(username)
                .password(personne.getPassword())
                .roles(personne.getClass().getSimpleName().toUpperCase())
                .build()
            )

            // Si l'utilisateur n'existe pas, on jète l'exception UsernameNotFoundException
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"))
        ;
    }
}
