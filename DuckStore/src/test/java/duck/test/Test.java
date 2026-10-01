package duck.test;

import java.time.LocalDate;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
	
		/*
		 * ========================= BOUTIQUES =========================
		 */

		Boutique paris = new Boutique(null, "DuckStore Paris", "42", "Rue du Canard", "Paris", "75001");

		Boutique lyon = new Boutique(null, "DuckStore Lyon", "7", "Quai des Palmipèdes", "Lyon", "69002");

		/*
		 * ========================= CONSOLES =========================
		 */

		Portable duckBoy = new Portable(null, "DuckBoy", 129.99, LocalDate.parse("2022-04-15"), 8, 6.0);

		Hybride duckSwitch = new Hybride(null, "DuckSwitch", 299.99, LocalDate.parse("2023-09-20"), 6, true);

		Salon duckStation = new Salon(null, "DuckStation 5", 499.99, LocalDate.parse("2024-11-05"), true);

		/*
		 * ========================= JEUX =========================
		 */

		Jeu duckSouls = new Jeu(null, "Duck Souls", duckStation, paris, Set.of(Genre.ACTION, Genre.RPG));

		Jeu quackOfTheWild = new Jeu(null, "The Legend of Duck: Quack of the Wild", duckSwitch, paris,
				Set.of(Genre.ACTION, Genre.AVENTURE, Genre.RPG));

		Jeu callOfDuck = new Jeu(null, "Call of Duck", duckStation, lyon, Set.of(Genre.ACTION, Genre.STRATEGIE));

		Jeu duckKart = new Jeu(null, "Super Duck Kart", duckSwitch, lyon, Set.of(Genre.SPORT, Genre.ACTION));

		Jeu pokemonDuck = new Jeu(null, "PokéDuck: Mare et Canards", duckBoy, paris, Set.of(Genre.RPG, Genre.AVENTURE));

		Jeu ageOfDucks = new Jeu(null, "Age of Ducks", duckBoy, lyon, Set.of(Genre.STRATEGIE));

		/*
		 * ========================= CLIENTS =========================
		 */

		Client alice = new Client(null, "alice.duck", "coincoin123", "Durand", "Alice", Civilite.Femme,
				"alice@duckmail.fr", "12", "Rue des Plumes", "Paris", "75011");

		Client bob = new Client(null, "bob.duck", "mare123", "Martin", "Bob", Civilite.Homme, "bob@duckmail.fr",
				"8", "Avenue de la Mare", "Montreuil", "93100");

		Client charlie = new Client(null, "charlie.duck", "plume123", "Petit", "Charlie", Civilite.NB,
				"charlie@duckmail.fr", "3", "Impasse du Coin-Coin", "Lyon", "69003");

		/*
		 * ========================= CARTES DE FIDELITE =========================
		 */

		Carte carteAlice = new Carte(null, "DUCK-0001", 420,alice);

		Carte carteBob = new Carte(null, "DUCK-0002", 180,bob);

		Carte carteCharlie = new Carte(null, "DUCK-0003", 50,charlie);


		/*
		 * ========================= EMPLOYES =========================
		 */

		Employe donald = new Employe(null, "donald", "picsou123", "Duck", "Donald", Civilite.Homme, 2450.00,paris);

		Employe daisy = new Employe(null, "daisy", "donald123", "Duck", "Daisy", Civilite.Femme, 2600.00,lyon);



		/*
		 * ========================= ACHATS
		 *
		 */

		alice.getAchats().add(duckSouls);
		alice.getAchats().add(quackOfTheWild);
		alice.getAchats().add(duckKart);

		bob.getAchats().add(callOfDuck);
		bob.getAchats().add(ageOfDucks);

		charlie.getAchats().add(pokemonDuck);
		charlie.getAchats().add(quackOfTheWild);

		/*
		 * ========================= PETITS TESTS =========================
		 */

		System.out.println("=== BOUTIQUES ===");
		System.out.println(paris);
		System.out.println(lyon);

		System.out.println();

		System.out.println("=== JEUX DE ALICE ===");
		for (Jeu jeu : alice.getAchats()) {
			System.out.println("- " + jeu.getTitre());
		}

		System.out.println();

		System.out.println("=== JEUX DE DUCKSWITCH ===");
		for (Jeu jeu : duckSwitch.getJeux()) {
			System.out.println("- " + jeu.getTitre());
		}

		System.out.println();

		System.out.println("=== JEUX RPG ===");
		for (Jeu jeu : paris.getJeux()) {
			if (jeu.getGenres().contains(Genre.RPG)) {
				System.out.println("- " + jeu.getTitre());
			}
		}


		System.out.println(alice.getPrenom() + " possède " + alice.getCarte().getPoints() + " points de fidélité.");
	}

}
