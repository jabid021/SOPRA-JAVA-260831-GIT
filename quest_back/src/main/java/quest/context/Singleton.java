package quest.context;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import quest.dao.DAOFiliere;
import quest.dao.DAOMatiere;
import quest.dao.DAOModule;
import quest.dao.DAOOrdinateur;
import quest.dao.DAOPersonne;
import quest.dao.DAOSalle;
import quest.dao.IDAOFiliere;
import quest.dao.IDAOMatiere;
import quest.dao.IDAOModule;
import quest.dao.IDAOOrdinateur;
import quest.dao.IDAOPersonne;
import quest.dao.IDAOSalle;

public class Singleton {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");

	private IDAOMatiere daoMatiere = new DAOMatiere();
	private IDAOOrdinateur daoOrdinateur = new DAOOrdinateur();
	private IDAOPersonne daoPersonne = new DAOPersonne();
	private IDAOFiliere daoFiliere = new DAOFiliere();
	private IDAOModule daoModule = new DAOModule();
	private IDAOSalle daoSalle = new DAOSalle();

	private static Singleton instance=null;

	private Singleton() {}


	public static Singleton getInstance() {
		if(instance==null) 
		{
			instance=new Singleton();
		}
		return instance;
	}

	public IDAOMatiere getDaoMatiere() {
		return daoMatiere;
	}
	

	public IDAOOrdinateur getDaoOrdinateur() {
		return daoOrdinateur;
	}


	public IDAOPersonne getDaoPersonne() {
		return daoPersonne;
	}


	public IDAOFiliere getDaoFiliere() {
		return daoFiliere;
	}


	public IDAOModule getDaoModule() {
		return daoModule;
	}


	public IDAOSalle getDaoSalle() {
		return daoSalle;
	}


	public EntityManagerFactory getEmf() {
		return emf;
	}


}
