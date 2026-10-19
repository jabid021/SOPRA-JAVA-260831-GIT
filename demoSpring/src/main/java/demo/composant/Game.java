package demo.composant;

import java.util.Random;

//@Component
public class Game {
	
	
	private IConfig audio;
	
	//@Autowired
	//@Qualifier("graphisme")
	private IConfig configGraphisme;
	
	private String nom;
	
	public Game() 
	{}

	public IConfig getAudio() {
		return audio;
	}

	public void setAudio(IConfig audio) {
		this.audio = audio;
	}

	public IConfig getConfigGraphisme() {
		return configGraphisme;
	}

	public void setConfigGraphisme(IConfig configGraphisme) {
		this.configGraphisme = configGraphisme;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	public void demoMetier() 
	{
		System.out.println("----------Traitement de la methode demoMetier dans un objet game----------");
		
	}
	
	public String demoMetier2() 
	{
		System.out.println("----------Traitement de la methode demoMetier2 dans un objet game----------");
		return "Resultat de la methode demoMetier2";
	}
	
	
	public String demoMetier3ExceptionPossible() throws Exception
	{
		Random r = new Random();
		int alea= r.nextInt(10);
		if(alea%2==0) 
		{
			throw new Exception("Pas de chance, c'est pair...");
		}
		System.out.println("Fin de la methode");
		return "TOUT EST OK "+alea;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Game [audio=" + audio + ", configGraphisme=" + configGraphisme + ", nom=" + nom + "]";
	}

	
	
	
	
}
