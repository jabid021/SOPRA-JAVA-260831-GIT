package quest;

import org.springframework.beans.factory.annotation.Autowired;

import quest.dao.IDAOMatiere;
import quest.model.Matiere;
import quest.service.SalleService;

public class Test {

	@Autowired
	IDAOMatiere daoMatiere;	
	
	
	@Autowired
	SalleService salleSrv;
	
	
	public void run() 
	{
		Matiere m1 = new Matiere(null,"DEMO");
		daoMatiere.save(m1);
		
		System.out.println(daoMatiere.findAll());
		
		System.out.println(salleSrv.getAll());
		
		
	}
}
