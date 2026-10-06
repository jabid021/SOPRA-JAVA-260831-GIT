package hopital.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import hopital.context.Singleton;
import hopital.dao.IDAOCompte;
import hopital.dao.IDAOVisite;
import hopital.model.Compte;
import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Secretaire;
import hopital.model.Visite;
import hopital.service.PatientService;

public class App {

	public static IDAOCompte daoCompte = Singleton.getInstance().getDaoCompte();
	public static IDAOVisite daoVisite =Singleton.getInstance().getDaoVisite();
	public static PatientService patientSrv = Singleton.getInstance().getPatientService();
	
	
	static File fichier = new File("fileAttente.txt");
	static LinkedList<Patient> fileAttente = new LinkedList();
	static Compte connected = null;
	static boolean enPause=false;
	
	public static int saisieInt(String message) 
	{
		Scanner sc = new Scanner(System.in); 
		System.out.println(message);
		return sc.nextInt();
	}

	public static double saisieDouble(String message) 
	{
		Scanner sc = new Scanner(System.in); 
		System.out.println(message);
		return sc.nextDouble();
	}

	public static String saisieString(String message) 
	{
		Scanner sc = new Scanner(System.in); 
		System.out.println(message);
		return sc.nextLine();
	}

	public static boolean saisieBoolean(String message) 
	{
		Scanner sc = new Scanner(System.in); 
		System.out.println(message);
		return sc.nextBoolean();
	}
	
	public static void menuPrincipal() 
	{
		System.out.println("\n---Menu Principal");
		System.out.println("1 - Se connecter");
		System.out.println("2 - Stop");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
			case 1 : seConnecter();break;
			case 2 : System.exit(0);break;
		}
		menuPrincipal();
	}
	
	public static void seConnecter() 
	{
		String login = saisieString("Saisir login");
		String password = saisieString("Saisir password");
		connected = daoCompte.findByLoginAndPassword(login, password);
		
		if(connected==null) 
		{
			System.out.println("Identifiants invalides");
		}
		else if(connected instanceof Medecin) 
		{
			int salle = saisieInt("Saisir votre salle");
			((Medecin) connected).setSalle(salle);
			menuMedecin();
		}
		else if(connected instanceof Secretaire) 
		{
			if(enPause) {menuSecretairePause();}
			else {menuSecretaire();}
		}
		
	}
	
	public static void menuSecretaire() 
	{
		System.out.println("\n---Menu Secretaire-----");
		System.out.println("1 - Accueillir un patient");
		System.out.println("2 - Afficher les anciennes visites d'un patient");
		System.out.println("3 - Afficher file d'attente");
		System.out.println("4 - Supprimer un patient (RGPD)");
		System.out.println("5 - Partir en pause");
		System.out.println("6 - Se deconnecter");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
			case 1 : accueillirPatient();break;
			case 2 : afficherVisitesPatient();break;
			case 3 : afficherFileAttente();;break;
			case 4 : supprimerPatient();break;
			case 5 : partirEnPause(); break;
			case 6: connected=null; menuPrincipal(); break;
		}
		menuSecretaire();
	}
	
	public static void accueillirPatient() 
	{
		int id = saisieInt("Saisir l'id du patient");
		Patient patient = patientSrv.getById(id);
		
		if(patient==null) 
		{
			System.out.println("---Creation d'un nouveau patient : -------");
			String prenom = saisieString("Saisir le prenom du patient");
			String nom = saisieString("Saisir le nom du patient");
			patient = new Patient(id, prenom, nom);
			patientSrv.insert(patient);
		}
		if(fileAttente.contains(patient)) 
		{
			System.out.println("Ce patient est deja dans la file d'attente");
		}
		else 
		{
			fileAttente.add(patient);
			System.out.println("Le patient "+patient+" a ete ajoute dans la file d'attente");
		}
	}
	
	public static void afficherVisitesPatient() 
	{
		int id = saisieInt("Saisir l'id du patient");
		Patient patient = patientSrv.getById(id);
		
		if(patient==null) 
		{
			System.out.println("Ce patient n'existe pas dans notre systeme");
		}
		
		else 
		{
			List<Visite> visites = daoVisite.findByPatientId(id);
			if(visites.isEmpty()) 
			{
				System.out.println("Ce patient n'a pas de visite");
			}
			for(Visite v : visites) 
			{
				System.out.println(v);
			}
		}
		
	}
	
	public static void afficherFileAttente() 
	{
		if(fileAttente.isEmpty()) 
		{
			System.out.println("Aucun patient dans la file d'attente");
		}
		else 
		{
			for(Patient p : fileAttente) 
			{
				System.out.println(p);
			}
			if(connected instanceof Medecin) 
			{
				System.out.println("Le prochain patient est "+fileAttente.peekFirst());
			}
		}
	}
	
	public static void supprimerPatient() 
	{
		int idPatient=saisieInt("Saisir l'id du patient a delete");
		daoVisite.updatePatientSetNull(idPatient);
		patientSrv.deleteById(idPatient);
		System.out.println("Patient "+idPatient+" delete de la bdd");
		
	}
	
	public static void partirEnPause() 
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(fichier);
			ObjectOutputStream oos  = new ObjectOutputStream(fos);
			
			oos.writeObject(fileAttente);
			fileAttente.clear();
			enPause=true;
			System.out.println("File d'attente save => SECRETAIRE EN PAUSE");
			
			oos.close();
			fos.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		menuSecretairePause();
	}
	
	public static void menuSecretairePause() {
		System.out.println("\n---Menu Secretaire Pause-----");
		System.out.println("1 - Revenir de Pause");
		System.out.println("2 - Se deconnecter");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
			case 1 : revenirDePause();break;
			case 2: connected=null; menuPrincipal(); break;
		}	
		menuSecretairePause();
	}
	
	public static void revenirDePause() 
	{
		try 
		{
			FileInputStream fis = new FileInputStream(fichier);
			ObjectInputStream ois  = new ObjectInputStream(fis);
			
			fileAttente=(LinkedList<Patient>) ois.readObject();
			enPause=false;
			System.out.println("File d'attente recup => SECRETAIRE DE RETOUR");
			
			ois.close();
			fis.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		menuSecretaire();
	}
	
	public static void menuMedecin() 
	{
		System.out.println("\n---Menu Medecin-----");
		System.out.println("1 - Recevoir un patient");
		System.out.println("2 - Afficher file d'attente");
		System.out.println("3 - Sauvegarder visites");
		System.out.println("4 - Consulter CA");
		System.out.println("5 - Se deconnecter");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
			case 1 : recevoirPatient();break;
			case 2 : afficherFileAttente();;break;
			case 3 : sauvegarderVisites();break;
			case 4 : consulterCA(); break;
			case 5:  seDeconnecterMedecin(); menuPrincipal(); break;
		}
		menuMedecin();
	}
	
	public static void recevoirPatient() 
	{
		if(fileAttente.isEmpty()) 
		{
			System.out.println("Aucun patient dans la file d'attente");
		}
		else 
		{
			Medecin medecin = (Medecin) connected;
			Patient patient = fileAttente.pollFirst();
			System.out.println("Creation d'une visite pour "+patient.getPrenom()+" "+patient.getNom());
			Visite visite = new Visite(patient,medecin);
			medecin.getVisites().add(visite);
			if(medecin.getVisites().size()==3) 
			{
				System.out.println("Limite de 3 visites atteinte -> SAVE AUTO");
				sauvegarderVisites();
			}		
		}
	}
	
	public static void sauvegarderVisites() 
	{
		Medecin medecin = (Medecin) connected;
		if(medecin.getVisites().isEmpty()) 
		{
			System.out.println("Aucune visite a save");
		}
		else 
		{
			System.out.println("----SAUVEGARDE DES VISITES : -------");
			for(Visite v : medecin.getVisites()) 
			{
				daoVisite.save(v);
				System.out.println(v);
			}
			medecin.getVisites().clear();
		}
	}
	
	public static void seDeconnecterMedecin() 
	{
		if(!((Medecin) connected).getVisites().isEmpty()) 
		{
			System.out.println("Attention, il ne faut pas se deco sans save....");
			sauvegarderVisites();
		}
		connected=null;
	}
	public static void consulterCA() 
	{
		String debut = saisieString("Saisir la date de debut");
		String fin = saisieString("Saisir la date de fin");
		System.out.println("Affichage du CA sur la periode : "+debut+" - "+fin);
		
		
		/*double somme = daoVisite.findSumByMedecinIdAndDateVisiteBetween(connected.getId(), debut,fin);
		System.out.println(somme+"€");*/
		
		List<Visite> visites = daoVisite.findByMedecinIdAndDateVisiteBetween(connected.getId(), debut,fin);
		
		/*double total = 0;
		for(Visite v : visites) 
		{
			total+=v.getPrix();
		}*/
		
		double total = visites.stream().peek(v->System.out.println(v)).mapToDouble(v->v.getPrix()).sum();
		
		System.out.println(total+"€");
	}
	
	public static void main(String[] args) {
		//for(Visite v : daoVisite.findAll()) {System.out.println(v);}
		menuPrincipal();

	}

}
