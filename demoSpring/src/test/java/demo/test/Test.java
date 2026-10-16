package demo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.Graphisme;

public class Test {
	
	public static void main(String[] args) {
		

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		Audio audio = (Audio) ctx.getBean("audio");
		
		Graphisme gr = ctx.getBean(Graphisme.class);
		
		//Audio audio2 = ctx.getBean("audio2",Audio.class);
		
		//System.out.println(audio2);

		Game game = ctx.getBean(Game.class);
		
		
		System.out.println(game);
	}
}
