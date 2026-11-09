package fr.bibliotek.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bibliotek.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    public Optional<Utilisateur> findByUsername(String username);
}
