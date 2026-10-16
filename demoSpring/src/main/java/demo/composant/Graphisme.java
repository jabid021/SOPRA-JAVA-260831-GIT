package demo.composant;

public class Graphisme implements IConfig{

	private boolean fenetre;
	
	public Graphisme() {}

	public boolean isFenetre() {
		return fenetre;
	}

	public void setFenetre(boolean fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public String toString() {
		return "Graphisme [fenetre=" + fenetre + "]";
	}
	
	
	
}
