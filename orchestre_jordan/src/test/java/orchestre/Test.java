package orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import orchestre.config.AppConfig;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;

public class Test {
@Autowired
IMusicien guitariste;
@Autowired
Pianiste pianiste;
@Autowired
IMusicien flutiste;


	public void main(String[] args) {
		
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		AnnotationConfigApplicationContext ctx = new  AnnotationConfigApplicationContext(AppConfig.class);
		
		/*IMusicien guitariste = ctx.getBean(Guitariste.class);
		Pianiste pianiste = ctx.getBean(Pianiste.class); //Ne marchera plus avec AOP...
		IMusicien flutiste = (Flutiste) ctx.getBean("flutiste");*/
		
		guitariste.jouer();
		pianiste.jouer();
		flutiste.jouer();
	}
}
