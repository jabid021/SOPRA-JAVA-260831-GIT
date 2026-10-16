package orchestre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import orchestre.model.Flute;
import orchestre.model.Flutiste;
import orchestre.model.Guitare;
import orchestre.model.Guitariste;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;
import orchestre.model.Piano;

@Configuration
public class AppConfig {

	@Bean
	public Piano piano(){return new Piano();}
	@Bean
	public Guitare guitare(){return new Guitare();}
	@Bean
	public Flute flute(){return new Flute();}

	@Bean
	public IMusicien guitariste() 
	{
		Guitariste guitariste = new Guitariste();
		guitariste.setPrenom("Jordan");
		guitariste.setInstrument(guitare());
		return guitariste ;
	}

	@Bean
	public IMusicien pianiste()
	{
		Pianiste pianiste = new Pianiste();
		pianiste.setPrenom("Eric");
		pianiste.setInstrument(piano());
		return pianiste ;
	}


	@Bean
	public IMusicien flutiste()
	{
		Flutiste flutiste = new Flutiste();
		flutiste.setPrenom("Olivier");
		flutiste.setFlute(flute());
		return flutiste ;
	}

}
