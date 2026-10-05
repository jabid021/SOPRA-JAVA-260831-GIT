package duck.dao;

import java.util.List;

import duck.context.Singleton;
import duck.model.Console;
import duck.model.Hybride;
import duck.model.Portable;
import duck.model.Salon;
import jakarta.persistence.EntityManager;

public class DAOConsole implements IDAOConsole{

	@Override
	public List<Console> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Console> consoles = em.createQuery("FROM Console").getResultList();
		em.close();
		return consoles;
	}

	@Override
	public Console findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			Console console = em.find(Console.class, id);
		em.close();
		return console;
	}

	@Override
	public Console save(Console console) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				console=em.merge(console);
			em.getTransaction().commit();
		em.close();
		return console;
	}

	@Override
	public void delete(Console console) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				console=em.merge(console);
				em.remove(console);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				Console console= em.find(Console.class, id);
				em.remove(console);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Portable> findAllPortable() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Portable> consoles = em.createQuery("FROM Portable").getResultList();
	em.close();
	return consoles;
	}

	@Override
	public List<Hybride> findAllHybride() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Hybride> consoles = em.createQuery("FROM Hybride").getResultList();
	em.close();
	return consoles;
	}

	@Override
	public List<Salon> findAllSalon() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Salon> consoles = em.createQuery("FROM Salon").getResultList();
	em.close();
	return consoles;
	}

	

}
