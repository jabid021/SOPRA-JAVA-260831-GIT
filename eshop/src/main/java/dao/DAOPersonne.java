package dao;

import java.util.List;

import context.Singleton;
import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Personne;
import jakarta.persistence.EntityManager;

public class DAOPersonne implements IDAOPersonne{

	@Override
	public List<Personne> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Personne>personnes = em.createQuery("FROM Personne").getResultList();
		em.close();
		return personnes;
	}

	@Override
	public Personne findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Personne personne = em.find(Personne.class, id);
		em.close();
		return personne;
	}

	@Override
	public Personne save(Personne personne) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		personne=em.merge(personne);
		em.getTransaction().commit();
		em.close();
		return personne;
	}

	@Override
	public void delete(Personne personne) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		personne=em.merge(personne);
		em.remove(personne);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Personne personne= em.find(Personne.class, id);
		em.remove(personne);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Fournisseur> fournisseurs = em.createQuery("FROM Fournisseur").getResultList();
		em.close();
		return fournisseurs;
	}

	@Override
	public List<Client> findAllClient() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Client> clients = em.createQuery("FROM Client").getResultList();
		em.close();
		return clients;
	}

	@Override
	public Client findByIdWithAchats(Integer idClient) {
		Client client = null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
			client = em.createQuery("SELECT c FROM Client c LEFT JOIN FETCH c.achats where c.id=:id",Client.class).setParameter("id",idClient).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		em.close();
		return client;
	}
	

	@Override
	public Fournisseur findByIdWithStock(Integer idFournisseur) {
		Fournisseur fournisseur = null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
			fournisseur = em.createQuery("SELECT f FROM Fournisseur f LEFT JOIN FETCH f.stock where f.id=:id",Fournisseur.class).setParameter("id",idFournisseur).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		em.close();
		return fournisseur;
	}



}
