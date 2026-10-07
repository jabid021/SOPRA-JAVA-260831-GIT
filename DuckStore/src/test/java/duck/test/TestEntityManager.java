package duck.test;

import java.util.List;

import duck.context.Singleton;
import duck.model.Achat;
import duck.model.Boutique;
import duck.model.Compte;
import duck.model.Jeu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class TestEntityManager {

	public static void main(String[] args) {
		
		//Commandes  SQL et leur equivalent en JPA
		
		//Avec JPA, on ne fait plus du SQL, on fait JPQL (Java persistence query language)
		
		//SELECT BY ID => em.find(Class.class,id)
			//Si l'id existe => return l'objet
			//Sinon return null
		//SELECT ALL => em.createQuery(From Classe)
		//SELECT WHERE => em.createQuery(SELECT o FROM Classe o where o.x...)
		
		
		//Par defaut, dans toutes les entities, le chargement des listes (XToMany) est en mode Lazy (!=Eager)
			//On peut changer ce comportement en modifiant l'attribut fetch = Eager dans le @XToMany (NE JAMAIS FAIRE CA)
			//Faire une requete qui va s'occuper de charger la liste quand on a besoin
		
		//Avec em.createQuery :
			//Si plusieurs resultats attendus => getResultList()
				//Si la requete sort plusieurs resultat => OK
				//Si la requete sort un seul resultat => OK
				//Si le requete ne sort aucun resultat => retourne un tableau empty
			
			//Si un seul resultat attendu => getSingleResult
				//Si la requete sort bien un resultat => OK
				//Si la requete ne sort rien => JPA genere une exception (Faire un try catch)
		
		
	//Toutes les requetes effectuant une modif en bdd, il faut  begin / commit / rollback au debut des requetes / à la fin
		
		//INSERT => em.persist(objet)
			//En auto increment, si on persit un objet avec un ID, JPA pas content !
			//Sans auto increment, si on persist un objet SANS ID, JPA pas content !

		//UPDATE => em.merge(objet)
				//Si objet a un id qui n'existe pas / null => insert
				//Si objet a un id existant => update
			
		//UPDATE WHERE => UPDATE Class c set c.x...
		//DELETE => em.remove(object) => object DOIT etre MANAGED
		
		
		//Avec JPA, les objets peuvent / doivent etre managed
		
			//TOUS LES SELECT (find,createQuery) return des objets managed
		
			//objet = em.find(Class.class,id) => objet est managed
			//List<Objet> obj = em.createQuery(From Class) => tous les objets sont managed 
			//em.persist(objet) => objet est managed par JPA
			//Boutique copie = em.merge(objet) => objet n'est PAS managed par JPA, copie EST managed !
		
		
		
		
		//Boutique paris = new Boutique(null,"DuckStore Paris-UPDATE2", "42", "Rue du Canard", "Paris", "75001");
		//Boutique paris2 = new Boutique(15,"DuckStore Paris-UPDATE3", "42", "Rue du Canard", "Paris", "75001");
		//Boutique boutiqueDelete = new Boutique(9, null, null, null, null, null);
		
		
		
		EntityManagerFactory emf = Singleton.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		
			em.getTransaction().begin();
				
				//paris = em.merge(paris);
				//paris2 = em.merge(paris2);
				
				//boutiqueDelete = em.merge(boutiqueDelete);
				//em.remove(boutiqueDelete);
				
				//Boutique boutique = em.find(Boutique.class,8);
				//em.remove(boutique);
			
			//em.createQuery("UPDATE Jeu j set j.prix = j.prix*1.5").executeUpdate();
				
			em.getTransaction().commit();
		
		
			Boutique b = em.find(Boutique.class, 1);
			List<Boutique> boutiques = em.createQuery("FROM Boutique").getResultList();
			List<Boutique> boutiques2 = em.createQuery("SELECT b FROM Boutique b").getResultList();
			
			List<String> nomsDesBoutiques = em.createQuery("SELECT b.nom FROM Boutique b").getResultList();
			
			List<Jeu> jeuxPlus25 = em.createQuery("SELECT j FROM Jeu j where j.prix>=26").getResultList();
			
			System.out.println("-----Liste des comptes : -----\n"+em.createQuery("From Compte").getResultList());
			
			System.out.println("-----Liste des Employes : -----\n"+em.createQuery("From Employe").getResultList());
			
			System.out.println("-----Liste des Clients : -----\n"+em.createQuery("From Client").getResultList());
			
			System.out.println("-----Liste des Console Portable : -----\n"+em.createQuery("From Portable").getResultList());
			
			
			double prix = 26;
			List<Jeu> jeuxPlus26 = em.createQuery("SELECT j FROM Jeu j where j.prix>=:prix").setParameter("prix", prix).getResultList();
			
			//Jeu jeu = em.find(Jeu.class,1);
			
			Jeu jeu = em.createQuery("SELECT j from Jeu j LEFT JOIN FETCH j.ventes v where j.id=:id",Jeu.class).setParameter("id", 1).getSingleResult();
			
			
			
			
			
			String login = "donald";
			String password = "picsou12";
			
			try {
			Compte connected = 
					em.createQuery("SELECT c FROM Compte c where c.login=:log and c.password=:pass",Compte.class)
					.setParameter("log", login)
					.setParameter("pass", password)
					.getSingleResult();
			
			System.out.println(connected);
			}
			catch(Exception e) {}
			/*Query requete = em.createQuery("SELECT j FROM Jeu j where j.prix>=:prix");
			requete.setParameter("prix",prix);
			List<Jeu> jeuxPlus26 = requete.getResultList();*/
		
		em.close();
		emf.close();
		
		System.out.println(nomsDesBoutiques);
		
		System.out.println(jeuxPlus25);
		//System.out.println(b);
		for(Boutique bou : boutiques) 
		{
			System.out.println(bou);
		}
		
		
		
		
		
		System.out.println("-----Fiche du Jeu numero 1 : ----------");
		
		System.out.println(jeu);
		
		System.out.println("Voici les ventes du jeu numero 1 :");
		
		if(jeu.getVentes().isEmpty()) {System.out.println("Ce jeu n'a jamais ete vendu");}
		
		for(Achat a : jeu.getVentes()) 
		{
			System.out.println(a);
		}
		
		
		
	}

}
