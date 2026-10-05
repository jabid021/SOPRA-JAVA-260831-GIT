package duck.dao;

import java.util.List;

import duck.context.Singleton;
import duck.model.Boutique;
import duck.model.Jeu;
import jakarta.persistence.EntityManager;

public class DAOJeu implements IDAOJeu{

	@Override
	public List<Jeu> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Jeu> jeus = em.createQuery("FROM Jeu").getResultList();
		em.close();
		return jeus;
	}

	@Override
	public Jeu findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			Jeu jeu = em.find(Jeu.class, id);
		em.close();
		return jeu;
	}

	@Override
	public Jeu save(Jeu jeu) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				jeu=em.merge(jeu);
			em.getTransaction().commit();
		em.close();
		return jeu;
	}

	@Override
	public void delete(Jeu jeu) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				jeu=em.merge(jeu);
				em.remove(jeu);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				Jeu jeu= em.find(Jeu.class, id);
				em.remove(jeu);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public Jeu findByIdWithVentes(Integer idJeu) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Jeu jeu = null;
		 try {
			 jeu = 
				em.createQuery("SELECT j FROM Jeu j LEFT JOIN FETCH j.ventes where j.id=:id",Jeu.class)
				.setParameter("id", idJeu)
				.getSingleResult();
		 }catch(Exception e) {e.printStackTrace();}
		em.close();
		return jeu;
	}

	

}
