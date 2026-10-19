package demo.test;

import org.springframework.beans.factory.annotation.Autowired;

import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.Graphisme;

public class Test {
	
	
	@Autowired
	Audio audio;
	
	@Autowired
	Graphisme gr;
	
	@Autowired
	Game game;
	

	public void run(String[] ...args) {
		

		//Config principale en XML 
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		//Config principale en Java
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//Audio audio = (Audio) ctx.getBean(Audio.class);
		
		//Graphisme gr = ctx.getBean(Graphisme.class);
		
		//Audio audio2 = ctx.getBean("audio2",Audio.class);
		
		//System.out.println(audio2);

		//Game game = ctx.getBean(Game.class);
		System.out.println(audio);
		
		System.out.println(gr);
		
		System.out.println(game);
		
		System.out.println("\n--------Debut DEMO AOP-----------\n");
		game.demoMetier();
		
		
		game.demoMetier2();
		
		try {
		game.demoMetier3ExceptionPossible();
		}catch(Exception e) {e.printStackTrace();}
		
		System.out.println("\n--------FIN DEMO AOP-----------\n");
		
		game.toString();
	}
}
