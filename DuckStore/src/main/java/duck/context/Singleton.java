package duck.context;

import duck.dao.DAOAchat;
import duck.dao.DAOBoutique;
import duck.dao.DAOCarte;
import duck.dao.DAOCompte;
import duck.dao.DAOConsole;
import duck.dao.DAOJeu;
import duck.dao.DAOPaiement;
import duck.dao.IDAOAchat;
import duck.dao.IDAOBoutique;
import duck.dao.IDAOCarte;
import duck.dao.IDAOCompte;
import duck.dao.IDAOConsole;
import duck.dao.IDAOJeu;
import duck.dao.IDAOPaiement;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Singleton {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
	
	private IDAOAchat daoAchat = new DAOAchat();
	private IDAOCompte daoCompte = new DAOCompte();
	private IDAOBoutique daoBoutique = new DAOBoutique();
	private IDAOJeu daoJeu = new DAOJeu();
	private IDAOConsole daoConsole = new DAOConsole();
	private IDAOPaiement daoPaiement = new DAOPaiement();
	private IDAOCarte daoCarte = new DAOCarte();
	
	private static Singleton instance=null;
	
	private Singleton() {}

	public static Singleton getInstance() {
		if(instance==null) 
		{
			instance=new Singleton();
		}
		return instance;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IDAOAchat getDaoAchat() {
		return daoAchat;
	}

	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}

	public IDAOBoutique getDaoBoutique() {
		return daoBoutique;
	}

	public IDAOJeu getDaoJeu() {
		return daoJeu;
	}

	public IDAOConsole getDaoConsole() {
		return daoConsole;
	}

	public IDAOPaiement getDaoPaiement() {
		return daoPaiement;
	}

	public IDAOCarte getDaoCarte() {
		return daoCarte;
	}



	
}
