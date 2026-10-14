package quest.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quest.context.Singleton;
import quest.model.Filiere;

public class DAOFiliere implements IDAOFiliere{

	@Override
	public List<Filiere> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Filiere>filieres = em.createQuery("FROM Filiere").getResultList();
		em.close();
		return filieres;
	}

	@Override
	public Filiere findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			Filiere filiere = em.find(Filiere.class, id);
		em.close();
		return filiere;
	}

	@Override
	public Filiere save(Filiere filiere) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				filiere=em.merge(filiere);
			em.getTransaction().commit();
		em.close();
		return filiere;
	}

	@Override
	public void delete(Filiere filiere) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				filiere=em.merge(filiere);
				em.remove(filiere);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				Filiere filiere= em.find(Filiere.class, id);
				em.remove(filiere);
			em.getTransaction().commit();
		em.close();
	}

}
