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
	
		Client client1 = new Client(null,"Abid","Jordan",Genre.homme,LocalDate.parse("1993-05-01") ,"1 bis","Rue de paris","Paris","75009");
		Fournisseur fournisseur1 = new Fournisseur(null,"Doe","John", Genre.nb,"AJC");
		
		Produit produit1 = new Produit(null,"Formation SQL",849.99,fournisseur1);
		Produit produit2 = new Produit(null,"Formation Spring",1350,fournisseur1);
		
		client1.getAchats().add(produit1);
		client1.getAchats().add(produit2);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
			em.persist(client1);
			em.persist(fournisseur1);
			em.persist(produit1);
			em.persist(produit2);
		
		em.getTransaction().commit();
		
		em.close();
		
		
		emf.close();

	}

}
