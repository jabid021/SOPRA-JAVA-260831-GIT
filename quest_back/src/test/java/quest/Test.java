package quest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import quest.dao.IDAOFiliere;
import quest.dao.IDAOMatiere;
import quest.dao.IDAOModule;
import quest.model.Filiere;
import quest.model.Matiere;
import quest.service.SalleService;

public class Test {

	@Autowired
	IDAOMatiere daoMatiere;	
	
	@Autowired
	IDAOModule daoModule;
	
	@Autowired
	IDAOFiliere daoFiliere;
	
	@Autowired
	SalleService salleSrv;
	
	
	public void run() 
	{
		Matiere m1 = new Matiere(null,"DEMO");
		daoMatiere.save(m1);
		System.out.println(m1);
		
	//	System.out.println(daoMatiere.findAll());
		
		//System.out.println(salleSrv.getAll());
		
		//System.out.println(daoMatiere.findByLibelleContaining("S"));

		for(Filiere f : daoFiliere.findAll()) 
		{
			System.out.println(f);
		}
		System.out.println(daoFiliere.findFiliereDateApres(LocalDate.parse("2026-04-06")));
		System.out.println(daoFiliere.findByDebutAfter(LocalDate.parse("2026-04-06")));

		System.out.println("--------------");
		System.out.println(daoMatiere.findAllByOrderByLibelleDesc());
		System.out.println(daoMatiere.countByLibelleContaining("S"));
		
	}
}
