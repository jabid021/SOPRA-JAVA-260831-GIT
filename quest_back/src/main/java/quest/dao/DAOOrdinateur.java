package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import quest.model.Ordinateur;

@Repository 
@Transactional
public class DAOOrdinateur implements IDAOOrdinateur{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Ordinateur> findAll() {
		return em.createQuery("FROM Ordinateur").getResultList();
	}

	@Override
	public Ordinateur findById(Integer id) {
		return em.find(Ordinateur.class, id);
	}

	@Override
	public Ordinateur save(Ordinateur ordinateur) {
		return em.merge(ordinateur);
	}

	@Override
	public void delete(Ordinateur ordinateur) {
		ordinateur=em.merge(ordinateur);
		em.remove(ordinateur);
	}

	@Override
	public void deleteById(Integer id) {
		Ordinateur ordinateur= em.find(Ordinateur.class, id);
		em.remove(ordinateur);
	}

}
