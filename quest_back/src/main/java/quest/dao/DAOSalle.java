package quest.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quest.context.Singleton;
import quest.model.Salle;

public class DAOSalle implements IDAOSalle{

	@Override
	public List<Salle> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Salle>salles = em.createQuery("FROM Salle").getResultList();
		em.close();
		return salles;
	}

	@Override
	public Salle findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			Salle salle = em.find(Salle.class, id);
		em.close();
		return salle;
	}

	@Override
	public Salle save(Salle salle) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				salle=em.merge(salle);
			em.getTransaction().commit();
		em.close();
		return salle;
	}

	@Override
	public void delete(Salle salle) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				salle=em.merge(salle);
				em.remove(salle);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				Salle salle= em.find(Salle.class, id);
				em.remove(salle);
			em.getTransaction().commit();
		em.close();
	}

}
