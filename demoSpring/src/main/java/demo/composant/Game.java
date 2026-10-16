package demo.composant;

public class Game {
	
	private IConfig audio;
	private IConfig configGraphisme;
	
	private String nom;
	
	public Game() {}

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
