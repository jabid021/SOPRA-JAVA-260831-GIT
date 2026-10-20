package eshop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import eshop.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository 
@Transactional
public class DAOProduit implements IDAOProduit{

	@PersistenceContext
	private EntityManager em;

	
	@Override
	public List<Produit> findAll() {
		return em.createQuery("FROM Produit").getResultList();
	}

	@Override
	public Produit findById(Integer id) {
		return em.find(Produit.class, id);
	}

	@Override
	public Produit save(Produit produit) {
		return em.merge(produit);
	}

	@Override
	public void delete(Produit produit) {
		produit=em.merge(produit);
		em.remove(produit);
	}

	@Override
	public void deleteById(Integer id) {
		Produit produit= em.find(Produit.class, id);
		em.remove(produit);
	}

	@Override
	public List<Produit> findByLibLike(String libelle) {
		return em.createQuery("SELECT p FROM Produit p where p.libelle like :lib").setParameter("lib", libelle) .getResultList();
	}

	
	@Override
	public List<Produit> findByLibelleContaining(String libelle) {
		return em.createQuery("SELECT p FROM Produit p where p.libelle like :lib").setParameter("lib", "%"+libelle+"%") .getResultList();
	}
	
	@Override
	public Produit findByIdWithVentes(Integer idProduit) {
		Produit produit = null;
		try {
			produit = em.createQuery("SELECT p FROM Produit p LEFT JOIN FETCH p.ventes where p.id=:id",Produit.class).setParameter("id",idProduit).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		return produit;
	}

	

}
