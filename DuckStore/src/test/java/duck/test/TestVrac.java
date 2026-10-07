package duck.test;

import java.util.List;

import duck.context.Singleton;
import duck.model.Boutique;
import duck.model.CB;
import duck.model.Civilite;
import duck.model.Client;
import duck.model.Employe;
import duck.model.Paiement;
import duck.model.Paypal;
import duck.model.Portable;
import jakarta.persistence.EntityManager;

public class TestVrac {

	public static void main(String[] args) {

		Boutique paris = new Boutique(null, "DuckStore Paris", "42", "Rue du Canard", "Paris", "75001");

		Employe donald = new Employe(null, "toto2", "picsou123", "Duck", "Donald", Civilite.Homme, 2450.00,paris);

		Client alice = new Client(null, "alice.duck", "coincoin123", "Durand", "Alice", Civilite.Femme,
				"alice@duckmail.fr", "12", "Rue des Plumes", "Paris", "75011");

		Paiement paiementAlice = new CB("4856");
		Paiement paiementAlice2 = new Paypal(true);


		Paiement paiementBob = new CB("1234");

		Paiement paiementCharlie = new CB("7845");

		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();


		/*em.getTransaction().begin();
				em.persist(paris);
				em.persist(donald);
				em.persist(alice);
				em.persist(paiementAlice);
				em.persist(paiementAlice2);
				em.persist(paiementBob);
				em.persist(paiementCharlie);
			em.getTransaction().commit();
		 */

		//List<Compte> comptes = em.createQuery("FROM Compte").getResultList();
		//List<Client> clients = em.createQuery("FROM Client").getResultList();
		
		//List<Console> consoles = em.createQuery("FROM Console").getResultList();
		
		//List<Portable> portables = em.createQuery("FROM Portable").getResultList();
		
		List<Paiement> paiements = em.createQuery("FROM Paiement").getResultList();
		
		//List<CB> cbs = em.createQuery("FROM CB").getResultList();
		em.close();

		System.out.println("Liste des Comptes : ");
		//System.out.println(comptes);

		System.out.println("---------");

		System.out.println("Liste des Clients : ");
		//System.out.println(clients);

		System.out.println("\n---------");
		System.out.println("Liste des Consoles : ");
		//System.out.println(consoles);



		System.out.println("---------");

		System.out.println("Liste des Portables : ");
		//System.out.println(portables);

		System.out.println("\n---------");
		System.out.println("Liste des Paiement : ");
		System.out.println(paiements);


		System.out.println("---------");

		System.out.println("Liste des CB : ");
		//System.out.println(cbs);

	}

}
