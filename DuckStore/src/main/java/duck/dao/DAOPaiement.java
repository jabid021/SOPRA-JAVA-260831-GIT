package duck.dao;

import java.util.List;

import duck.context.Singleton;
import duck.model.CB;
import duck.model.Paiement;
import duck.model.Paypal;
import jakarta.persistence.EntityManager;

public class DAOPaiement implements IDAOPaiement{

	@Override
	public List<Paiement> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Paiement> paiements = em.createQuery("FROM Paiement").getResultList();
		em.close();
		return paiements;
	}

	@Override
	public Paiement findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Paiement paiement = em.find(Paiement.class, id);
		em.close();
		return paiement;
	}

	@Override
	public Paiement save(Paiement paiement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		paiement=em.merge(paiement);
		em.getTransaction().commit();
		em.close();
		return paiement;
	}

	@Override
	public void delete(Paiement paiement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		paiement=em.merge(paiement);
		em.remove(paiement);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Paiement paiement= em.find(Paiement.class, id);
		em.remove(paiement);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<CB> findAllCB() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<CB> paiements = em.createQuery("FROM CB").getResultList();
		em.close();
		return paiements;
	}

	@Override
	public List<Paypal> findAllPaypal() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Paypal> paiements = em.createQuery("FROM Paypal").getResultList();
		em.close();
		return paiements;
	}



}
