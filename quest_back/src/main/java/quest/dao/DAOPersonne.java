package quest.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quest.context.Singleton;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

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
	public List<Formateur> findAllFormateurs() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Formateur> formateurs = em.createQuery("FROM Formateur").getResultList();
	em.close();
	return formateurs;
	}

	@Override
	public List<Stagiaire> findAllStagiaires() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Stagiaire> stagiaires = em.createQuery("FROM Stagiaire").getResultList();
	em.close();
	return stagiaires;
	}

	@Override
	public Personne findByLoginAndPassword(String login, String password) {
		Personne connected = null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		try {
			connected = em.createQuery("SELECT p FROM Personne p where p.login=:login and p.password=:password",Personne.class).setParameter("login",login).setParameter("password",password).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		em.close();
		return connected;
	}

}
