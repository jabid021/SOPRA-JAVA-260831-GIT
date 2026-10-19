package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import quest.model.Salle;

@Repository 
@Transactional
public class DAOSalle implements IDAOSalle{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Salle> findAll() {
		return em.createQuery("FROM Salle").getResultList();
	}

	@Override
	public Salle findById(Integer id) {
		return em.find(Salle.class, id);
	}

	@Override
	public Salle save(Salle salle) {
		return em.merge(salle);
	}

	@Override
	public void delete(Salle salle) {
		salle=em.merge(salle);
		em.remove(salle);
	}

	@Override
	public void deleteById(Integer id) {
		Salle salle= em.find(Salle.class, id);
		em.remove(salle);
	}

}
