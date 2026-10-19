package eshop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository 
@Transactional
public class DAOPersonne implements IDAOPersonne{

	@PersistenceContext
	private EntityManager em;

	
	@Override
	public List<Personne> findAll() {
		return em.createQuery("FROM Personne").getResultList();
	}

	@Override
	public Personne findById(Integer id) {
		return em.find(Personne.class, id);
	}

	@Override
	public Personne save(Personne personne) {
		return em.merge(personne);
	}

	@Override
	public void delete(Personne personne) {
		personne=em.merge(personne);
		em.remove(personne);
	}

	@Override
	public void deleteById(Integer id) {
		Personne personne= em.find(Personne.class, id);
		em.remove(personne);
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		return em.createQuery("FROM Fournisseur").getResultList();
	}

	@Override
	public List<Client> findAllClient() {
		return em.createQuery("FROM Client").getResultList();
	}

	@Override
	public Client findByIdWithAchats(Integer idClient) {
		Client client = null;
		try {
			client = em.createQuery("SELECT c FROM Client c LEFT JOIN FETCH c.achats where c.id=:id",Client.class).setParameter("id",idClient).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		return client;
	}
	

	@Override
	public Fournisseur findByIdWithStock(Integer idFournisseur) {
		Fournisseur fournisseur = null;
		try {
			fournisseur = em.createQuery("SELECT f FROM Fournisseur f LEFT JOIN FETCH f.stock where f.id=:id",Fournisseur.class).setParameter("id",idFournisseur).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		return fournisseur;
	}



}
