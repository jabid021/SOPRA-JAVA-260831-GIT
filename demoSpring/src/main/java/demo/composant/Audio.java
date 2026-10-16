package demo.composant;

import org.springframework.stereotype.Component;

@Component //Par defaut, spring va creer un bean ayant le nom de la classe en minuscule (<bean id="audio"/>)
public class Audio implements IConfig{

	private int volume;
	private String typeSortie;
	
	public Audio() 
	{
		this.typeSortie="Stereo";
		this.volume=67;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getTypeSortie() {
		return typeSortie;
	}

	public void setTypeSortie(String typeSortie) {
		this.typeSortie = typeSortie;
	}

	@Override
	public String toString() {
		return "Audio [volume=" + volume + ", typeSortie=" + typeSortie + "]";
	}
	
	
	
}
