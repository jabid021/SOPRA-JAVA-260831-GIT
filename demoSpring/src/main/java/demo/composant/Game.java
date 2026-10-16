package demo.composant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Game {
	
	@Autowired
	private IConfig audio;
	
	@Autowired
	@Qualifier("graphisme")
	private IConfig configGraphisme;
	
	private String nom;
	
	public Game() 
	{
		this.nom="My Music Monsters";
	}

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

	@Override
	public String toString() {
		return "Game [audio=" + audio + ", configGraphisme=" + configGraphisme + ", nom=" + nom + "]";
	}

	
	
	
	
}
