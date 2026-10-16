package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.Graphisme;
import demo.composant.IConfig;

@Configuration
//@ComponentScan("demo.composant")
public class AppConfig {

	
		/*equivalent à 
		<bean id="makeAudio" class="demo.composant.Audio">
			<property name="volume" value="67"/>	
			<property name="typeSortie" value="Mono"/>
		</bean>*/
	@Bean
	public IConfig makeAudio() 
	{
		Audio audio  = new Audio();
		audio.setTypeSortie("Mono");
		audio.setVolume(67);
		return audio;
	}
	
	@Bean
	public Graphisme graph() 
	{
		return new Graphisme();
	}
	
	@Bean
	public Game game() 
	{
		Game g = new Game();
		g.setNom("My Singing Monsters");
		g.setAudio(makeAudio());
		g.setConfigGraphisme(graph());
		
		return g;
		
	}
	
}
