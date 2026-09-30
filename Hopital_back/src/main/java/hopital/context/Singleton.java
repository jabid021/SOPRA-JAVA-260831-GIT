package hopital.context;

import hopital.dao.DAOCompteJDBC;
import hopital.dao.DAOPatientJDBC;
import hopital.dao.DAOVisiteJDBC;
import hopital.dao.IDAOCompte;
import hopital.dao.IDAOPatient;
import hopital.dao.IDAOVisite;
import hopital.service.PatientService;

public class Singleton {

	//Avec le polymorphisme, on pourra switch facilement de JDBC à JPA (les deux etant des IDAOX)
	private IDAOCompte daoCompte = new DAOCompteJDBC();
	private IDAOVisite daoVisite = new DAOVisiteJDBC();
	private IDAOPatient daoPatient = new DAOPatientJDBC();
	private PatientService patientService = new PatientService();
	
	private static Singleton instance=null;
	
	//Impossible de creer un objet Singleton en dehors de cette page
	//Seule la methode getInstance() peut declencher ce constructeur
	private Singleton() {}
	

	//Ojn est sur que l'appli retournera toujours le meme objet "instance"
	//Si c'est la 1ere fois que la methode est call, instance est initialise avec new Singleton();
	//Sinon, retourne toujours le meme objet
	public static Singleton getInstance() {
		if(instance==null) 
		{
			instance=new Singleton();
		}
		return instance;
	}


	
	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}

	public IDAOVisite getDaoVisite() {
		return daoVisite;
	}

	public IDAOPatient getDaoPatient() {
		return daoPatient;
	}


	public PatientService getPatientService() {
		return patientService;
	}




	
	
	
	
	
}
