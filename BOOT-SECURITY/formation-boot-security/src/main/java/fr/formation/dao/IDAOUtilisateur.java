package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
    public Utilisateur findByUsername(String username);
}
