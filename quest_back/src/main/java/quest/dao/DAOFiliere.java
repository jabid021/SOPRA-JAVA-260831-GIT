package quest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import quest.model.Filiere;

@Repository 
@Transactional
public class DAOFiliere implements IDAOFiliere{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Filiere> findAll() {
		return em.createQuery("FROM Filiere").getResultList();
	}

	@Override
	public Filiere findById(Integer id) {
		return em.find(Filiere.class, id);
	}

	@Override
	public Filiere save(Filiere filiere) {
		return em.merge(filiere);
	}

	@Override
	public void delete(Filiere filiere) {
		filiere=em.merge(filiere);
		em.remove(filiere);
	}

	@Override
	public void deleteById(Integer id) {
		Filiere filiere= em.find(Filiere.class, id);
		em.remove(filiere);
	}

}
