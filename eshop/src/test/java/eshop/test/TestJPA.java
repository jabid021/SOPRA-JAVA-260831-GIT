package eshop.test;

import eshop.model.Personne;
import eshop.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
	
		Personne p1 = new Personne(null,"Abid","Jordan");
		Personne p2 = new Personne(null,"Doe","John");
		
		Produit pr1 = new Produit(null,"Formation SQL",849.99);
		Produit pr2 = new Produit(null,"Formation Spring",1350);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
			em.persist(p1);
			em.persist(p2);
			em.persist(pr1);
			em.persist(pr2);
		
		em.getTransaction().commit();
		
		em.close();
		
		
		emf.close();

	}

}
