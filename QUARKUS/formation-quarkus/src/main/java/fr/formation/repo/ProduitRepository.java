package fr.formation.repo;

import fr.formation.model.Produit;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProduitRepository implements PanacheRepositoryBase<Produit, Integer> {

}
