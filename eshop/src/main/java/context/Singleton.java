package context;

import dao.DAOAchat;
import dao.DAOPersonne;
import dao.DAOProduit;
import dao.IDAOAchat;
import dao.IDAOPersonne;
import dao.IDAOProduit;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Singleton {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
	
	private IDAOAchat daoAchat = new DAOAchat();
	private IDAOPersonne daoPersonne = new DAOPersonne();
	private IDAOProduit daoProduit = new DAOProduit();
	
	
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

	public IDAOPersonne getDaoPersonne() {
		return daoPersonne;
	}

	public IDAOProduit getDaoProduit() {
		return daoProduit;
	}

	



	
}
