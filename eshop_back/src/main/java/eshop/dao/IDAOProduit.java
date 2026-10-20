package eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.model.Produit;

public interface IDAOProduit extends JpaRepository<Produit,Integer> {

	@Query("SELECT p FROM Produit p where p.libelle like :lib")
	public List<Produit>  findByLibLike(@Param("lib") String libelle);

	@Query("SELECT p FROM Produit p LEFT JOIN FETCH p.ventes where p.id=:id")
	public	Produit  findByIdWithVentes(@Param("id")  Integer idProduit);
	
	public List<Produit> findByLibelleContaining(String libelle);
	
	public List<Produit> findByPrixBetween(double min, double max);
	
	public List<Produit> findByPrixLessThan(double max);
}
