package demo.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.Graphisme;
import demo.config.AppConfig;

public class Test {
	
	public static void main(String[] args) {
		

		//Config principale en XML 
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		//Config principale en Java
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Audio audio = (Audio) ctx.getBean(Audio.class);
		
		Graphisme gr = ctx.getBean(Graphisme.class);
		
		//Audio audio2 = ctx.getBean("audio2",Audio.class);
		
		//System.out.println(audio2);

		Game game = ctx.getBean(Game.class);
		System.out.println(audio);
		System.out.println(ctx.getBean("makeAudio"));
		
		System.out.println(game);
	}
}
