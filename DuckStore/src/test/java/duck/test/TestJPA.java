package duck.test;

import duck.model.Boutique;
import duck.model.Carte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
	
		Boutique paris = new Boutique(null, "DuckStore Paris", "42", "Rue du Canard", "Paris", "75001");

		Boutique lyon = new Boutique(null, "DuckStore Lyon", "7", "Quai des Palmipèdes", "Lyon", "69002");
		
		
		Carte carteAlice = new Carte(null, "DUCK-0001", 420,null);

		Carte carteBob = new Carte(null, "DUCK-0002", 180,null);

		Carte carteCharlie = new Carte(null, "DUCK-0003", 50,null);
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
			em.persist(paris);
			em.persist(lyon);
			em.persist(carteAlice);
			em.persist(carteBob);
			em.persist(carteCharlie);
		
		em.getTransaction().commit();
		
		em.close();
		
		
		
		em = emf.createEntityManager();
			
			System.out.println(em.find(Boutique.class,1));
			
			System.out.println(em.createQuery("FROM Carte").getResultList());
		
			
		em.close();
		
		
		emf.close();

	}

}
