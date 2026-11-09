package fr.bibliotek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bibliotek.model.Editeur;

public interface EditeurRepository extends JpaRepository<Editeur, String> {

}
