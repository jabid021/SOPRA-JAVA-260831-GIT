package fr.bibliotek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bibliotek.model.Avis;

public interface AvisRepository extends JpaRepository<Avis, String> {

}
