package eshop.test;

import java.time.LocalDate;

import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Genre;
import eshop.model.Personne;
import eshop.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
	
		Personne p1 = new Client(null,"Abid","Jordan",Genre.homme,LocalDate.parse("1993-05-01") ,"1 bis","Rue de paris","Paris","75009");
		Personne p2 = new Fournisseur(null,"Doe","John", Genre.nb,"AJC");
		
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
