package duck.dao;

import java.util.List;

import duck.context.Singleton;
import duck.model.Client;
import duck.model.Compte;
import duck.model.Employe;
import jakarta.persistence.EntityManager;

public class DAOCompte implements IDAOCompte{

	@Override
	public List<Compte> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Compte> comptes = em.createQuery("FROM Compte").getResultList();
		em.close();
		return comptes;
	}

	@Override
	public Compte findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Compte compte = em.find(Compte.class, id);
		em.close();
		return compte;
	}

	@Override
	public Compte save(Compte compte) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		compte=em.merge(compte);
		em.getTransaction().commit();
		em.close();
		return compte;
	}

	@Override
	public void delete(Compte compte) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		compte=em.merge(compte);
		em.remove(compte);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Compte compte= em.find(Compte.class, id);
		em.remove(compte);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Client> findAllClient() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Client> comptes = em.createQuery("FROM Client").getResultList();
		em.close();
		return comptes;
	}

	@Override
	public Client findByIdWithAchats(Integer idClient) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Client client = null;
		 try {
			 client = 
				em.createQuery("SELECT c FROM Client c LEFT JOIN FETCH c.achats where c.id=:id",Client.class)
				.setParameter("id", idClient)
				.getSingleResult();
		 }catch(Exception e) {e.printStackTrace();}
		em.close();
		return client;
	}

	@Override
	public List<Employe> findAllEmploye() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Employe> comptes = em.createQuery("FROM Employe").getResultList();
		em.close();
		return comptes;
	}

	@Override
	public Compte findByLoginAndPassword(String login, String password) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Client client = null;
		 try {
			 client = 
				em.createQuery("SELECT c FROM Client c where login=:log and password=:pass",Client.class)
				.setParameter("log", login)
				.setParameter("pass", password)
				.getSingleResult();
		 }catch(Exception e) {e.printStackTrace();}
		em.close();
		return client;
	}



}
