package fr.bibliotek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bibliotek.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, String> {

}
