package fr.bibliotek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bibliotek.model.Collection;

public interface CollectionRepository extends JpaRepository<Collection, String> {

}
