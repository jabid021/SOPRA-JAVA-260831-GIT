package orchestre.model;

public class Flutiste implements IMusicien{

	private String prenom;

	private IInstrument flute;
	
	public Flutiste() 
	{
		this.prenom="Olivier";
	}
	

	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public IInstrument getFlute() {
		return flute;
	}


	public void setFlute(IInstrument flute) {
		this.flute = flute;
	}

	@Override
	public void jouer() {
		System.out.println("Le flutiste "+prenom+" joue ! "+flute.son());
		
	}
}
