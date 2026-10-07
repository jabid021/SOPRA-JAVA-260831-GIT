package hopital.dao;

import java.util.List;

import hopital.context.Singleton;
import hopital.model.Compte;
import jakarta.persistence.EntityManager;

public class DAOCompte implements IDAOCompte {

	@Override
	public List<Compte> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Compte>comptes = em.createQuery("FROM Compte").getResultList();
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
	public Compte findByLoginAndPassword(String login, String password) {
		Compte connected = null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
			connected = em.createQuery("SELECT c FROM Compte c where c.login=:login and c.password=:password",Compte.class).setParameter("login",login).setParameter("password",password).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		em.close();
		return connected;
	}

}
