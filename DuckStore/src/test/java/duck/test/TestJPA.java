package duck.test;

import java.time.LocalDate;
import java.util.Set;

import duck.model.Achat;
import duck.model.Boutique;
import duck.model.CB;
import duck.model.Carte;
import duck.model.Civilite;
import duck.model.Client;
import duck.model.Employe;
import duck.model.Genre;
import duck.model.Hybride;
import duck.model.Jeu;
import duck.model.Paiement;
import duck.model.Paypal;
import duck.model.Portable;
import duck.model.Salon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
	
		Boutique paris = new Boutique(null, "DuckStore Paris", "42", "Rue du Canard", "Paris", "75001");

		Boutique lyon = new Boutique(null, "DuckStore Lyon", "7", "Quai des Palmipèdes", "Lyon", "69002");
		
		
		Portable duckBoy = new Portable(null, "DuckBoy", 129.99, LocalDate.parse("2022-04-15"), 8, 6.0);

		Hybride duckSwitch = new Hybride(null, "DuckSwitch", 299.99, LocalDate.parse("2023-09-20"), 6, true);

		Salon duckStation = new Salon(null, "DuckStation 5", 499.99, LocalDate.parse("2024-11-05"), true);
		
		Jeu duckSouls = new Jeu(null, "Duck Souls", duckStation, paris, Set.of(Genre.ACTION, Genre.RPG),25.9997858);

		Jeu quackOfTheWild = new Jeu(null, "The Legend of Duck: Quack of the Wild", duckSwitch, paris,
				Set.of(Genre.ACTION, Genre.AVENTURE, Genre.RPG),25.99);

		Jeu callOfDuck = new Jeu(null, "Call of Duck", duckStation, lyon, Set.of(Genre.ACTION, Genre.STRATEGIE),25.99);
		
		Jeu duckKart = new Jeu(null, "Super Duck Kart", duckSwitch, lyon, Set.of(Genre.SPORT, Genre.ACTION),0);

		Jeu pokemonDuck = new Jeu(null, "PokéDuck: Mare et Canards", duckBoy, paris, Set.of(Genre.RPG, Genre.AVENTURE),25.99);

		Jeu ageOfDucks = new Jeu(null, "Age of Ducks", duckBoy, lyon, Set.of(Genre.STRATEGIE),25.99);
		
		Employe donald = new Employe(null, "donald", "picsou123", "Duck", "Donald", Civilite.Homme, 2450.00,paris);

		Employe daisy = new Employe(null, "daisy", "donald123", "Duck", "Daisy", Civilite.Femme, 2600.00,null);
		
		
		Client alice = new Client(null, "alice.duck", "coincoin123", "Durand", "Alice", Civilite.Femme,
				"alice@duckmail.fr", "12", "Rue des Plumes", "Paris", "75011");

		Client bob = new Client(null, "bob.duck", "mare123", "Martin", "Bob", Civilite.Homme, "bob@duckmail.fr",
				"8", "Avenue de la Mare", "Montreuil", "93100");

		Client charlie = new Client(null, "charlie.duck", "plume123", "Petit", "Charlie", Civilite.NB,
				"charlie@duckmail.fr", "3", "Impasse du Coin-Coin", "Lyon", "69003");

		
		
		Carte carteAlice = new Carte(null, "DUCK-0001", 420,alice);

		Carte carteBob = new Carte(null, "DUCK-0002", 180,bob);

		Carte carteCharlie = new Carte(null, "DUCK-0003", 50,charlie);
		
		alice.setCarte(carteAlice);
		
		Paiement paiementAlice = new CB("4856");
		Paiement paiementAlice2 = new Paypal(true);
		
		
		Paiement paiementBob = new CB("1234");
		
		Paiement paiementCharlie = new CB("7845");
		
		Achat achat1 = new Achat(alice,duckSouls,paiementAlice);
		Achat achat2 = new Achat(alice,quackOfTheWild,paiementAlice);
		Achat achat3 = new Achat(alice,duckKart,paiementAlice2);
		
		Achat achat4 = new Achat(bob,callOfDuck,paiementBob);
		Achat achat5 = new Achat(bob,ageOfDucks,paiementBob);
		
		Achat achat6 = new Achat(charlie,pokemonDuck,paiementCharlie);
		Achat achat7 = new Achat(charlie,quackOfTheWild,paiementCharlie);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
			em.persist(paris);
			em.persist(lyon);
			
			em.persist(donald);
			em.persist(daisy);
			
			em.persist(duckSwitch);
			em.persist(duckStation);
			em.persist(duckBoy);
			
			
			em.persist(duckSouls);
			em.persist(quackOfTheWild);
			em.persist(callOfDuck);
			em.persist(duckKart);
			em.persist(pokemonDuck);
			em.persist(ageOfDucks);
			
			
			em.persist(alice);
			em.persist(bob);
			em.persist(charlie);
			
			em.persist(carteAlice);
			em.persist(carteBob);
			em.persist(carteCharlie);
			
			em.persist(paiementAlice);
			em.persist(paiementAlice2);
			em.persist(paiementBob);
			em.persist(paiementCharlie);
			
			em.persist(achat1);
			em.persist(achat2);
			em.persist(achat3);
			em.persist(achat4);
			em.persist(achat5);
			em.persist(achat6);
			em.persist(achat7);
		
		

		em.getTransaction().commit();
		
		em.close();
		
		
		
		em = emf.createEntityManager();
			
			System.out.println(em.find(Boutique.class,1));
			
			System.out.println(em.createQuery("FROM Carte").getResultList());
			
			System.out.println(em.createQuery("SELECT j FROM Jeu j where j.prix>=20").getResultList());
		
			System.out.println(em.find(Jeu.class, 1));
			
		em.close();
		
		
		emf.close();

	}

}
