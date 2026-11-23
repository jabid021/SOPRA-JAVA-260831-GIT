package fr.formation.serviceproduit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.serviceproduit.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {

}
