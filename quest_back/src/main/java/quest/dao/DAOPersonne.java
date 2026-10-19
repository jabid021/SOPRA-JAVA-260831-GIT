package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

@Repository 
@Transactional
public class DAOPersonne implements IDAOPersonne{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Personne> findAll() {
		return  em.createQuery("FROM Personne").getResultList();
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
	public List<Formateur> findAllFormateurs() {
		return em.createQuery("FROM Formateur").getResultList();
	}

	@Override
	public List<Stagiaire> findAllStagiaires() {
		return em.createQuery("FROM Stagiaire").getResultList();
	}

	@Override
	public Personne findByLoginAndPassword(String login, String password) {
		Personne connected = null;
		try {
			connected = em.createQuery("SELECT p FROM Personne p where p.login=:login and p.password=:password",Personne.class).setParameter("login",login).setParameter("password",password).getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		return connected;
	}

	@Override
	public List<Stagiaire> findByOrdinateurIsNull() {
		return em.createQuery("SELECT s FROM Stagiaire s where s.ordinateur is null").getResultList();
	}

}
