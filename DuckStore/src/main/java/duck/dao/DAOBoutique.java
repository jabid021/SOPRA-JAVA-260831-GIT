package duck.dao;

import java.util.List;

import duck.context.Singleton;
import duck.model.Boutique;
import jakarta.persistence.EntityManager;

public class DAOBoutique implements IDAOBoutique{

	@Override
	public List<Boutique> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Boutique> boutiques = em.createQuery("FROM Boutique").getResultList();
		em.close();
		return boutiques;
	}

	@Override
	public Boutique findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			Boutique boutique = em.find(Boutique.class, id);
		em.close();
		return boutique;
	}

	@Override
	public Boutique save(Boutique boutique) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				boutique=em.merge(boutique);
			em.getTransaction().commit();
		em.close();
		return boutique;
	}

	@Override
	public void delete(Boutique boutique) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				boutique=em.merge(boutique);
				em.remove(boutique);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				Boutique boutique= em.find(Boutique.class, id);
				em.remove(boutique);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public Boutique findByIdWithStaff(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Boutique boutique = null;
		 try {
			 boutique = 
				em.createQuery("SELECT b FROM Boutique b LEFT JOIN FETCH b.staff where b.id=:id",Boutique.class)
				.setParameter("id", id)
				.getSingleResult();
		 }catch(Exception e) {e.printStackTrace();}
		em.close();
		return boutique;
	}

	@Override
	public Boutique findByIdWithCataloguef(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Boutique boutique = null;
		 try {
			 boutique = 
				em.createQuery("SELECT b FROM Boutique b LEFT JOIN FETCH b.catalogue where b.id=:id",Boutique.class)
				.setParameter("id", id)
				.getSingleResult();
		 }catch(Exception e) {e.printStackTrace();}
		em.close();
		return boutique;
	}

}
