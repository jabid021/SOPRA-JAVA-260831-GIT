package orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import orchestre.model.Guitariste;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;

public class Test {

@Autowired
IMusicien guitariste; //Va planter pendant AOP (il faut passer par un objet de type IMusicien

@Autowired
Pianiste pianiste;

@Autowired
@Qualifier("flutiste")
IMusicien olivier;



	public void run() {
		
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		//AnnotationConfigApplicationContext ctx = new  AnnotationConfigApplicationContext(AppConfig.class);
		
		/*IMusicien guitariste = ctx.getBean(Guitariste.class);
		Pianiste pianiste = ctx.getBean(Pianiste.class); //Ne marchera plus avec AOP...
		IMusicien flutiste = (Flutiste) ctx.getBean("flutiste");*/
		
		try{
			guitariste.jouer();
		}
		catch(Exception e) 
		{}

		
		/*
		 * 
		 Si tout se passe bien : 
		 
			 Le public s'installe
			 le guitariste Jordan joue ! La guitare fait glink glink
			 Le public applaudit
		
		
		Si fausse note : 
			Le public s'installe
			Fausse note....
			le guitariste Jordan joue mais fait une fausse note !
			Le public jette des tomates
		 * 
		 * */
		
		
		//pianiste.jouer();
		//olivier.jouer();
		
		//guitariste.toString();
	}
}
