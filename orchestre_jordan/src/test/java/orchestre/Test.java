package orchestre;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import orchestre.model.Flutiste;
import orchestre.model.Guitariste;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;

public class Test {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		IMusicien guitariste = ctx.getBean(Guitariste.class);
		Pianiste pianiste = ctx.getBean(Pianiste.class); //Ne marchera plus avec AOP...
		IMusicien flutiste = (Flutiste) ctx.getBean("flutiste");
		
		guitariste.jouer();
		pianiste.jouer();
		flutiste.jouer();
	}
}
