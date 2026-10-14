package quest.context;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import quest.dao.DAOMatiere;
import quest.dao.IDAOMatiere;

public class Singleton {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
	
	private IDAOMatiere daoMatiere = new DAOMatiere();
	
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

	public EntityManagerFactory getEmf() {
		return emf;
	}

	
}
