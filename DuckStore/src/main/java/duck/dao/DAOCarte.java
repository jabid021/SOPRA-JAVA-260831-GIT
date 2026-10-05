package duck.dao;

import java.util.List;

import duck.context.Singleton;
import duck.model.Carte;
import jakarta.persistence.EntityManager;

public class DAOCarte implements IDAOCarte{

	@Override
	public List<Carte> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Carte> cartes = em.createQuery("FROM Carte").getResultList();
		em.close();
		return cartes;
	}

	@Override
	public Carte findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			Carte carte = em.find(Carte.class, id);
		em.close();
		return carte;
	}

	@Override
	public Carte save(Carte carte) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				carte=em.merge(carte);
			em.getTransaction().commit();
		em.close();
		return carte;
	}

	@Override
	public void delete(Carte carte) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				carte=em.merge(carte);
				em.remove(carte);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				Carte carte= em.find(Carte.class, id);
				em.remove(carte);
			em.getTransaction().commit();
		em.close();
	}

	

}
