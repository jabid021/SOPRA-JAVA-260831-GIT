package orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import orchestre.model.Guitariste;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;

public class Test {

@Autowired
Guitariste guitariste; //Va planter pendant AOP (il faut passer par un objet de type IMusicien

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
		guitariste.jouer();
		pianiste.jouer();
		olivier.jouer();
		
		
		//guitariste.toString();
	}
}
