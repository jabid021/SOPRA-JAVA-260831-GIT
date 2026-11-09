package fr.bibliotek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bibliotek.model.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, String> {

}
