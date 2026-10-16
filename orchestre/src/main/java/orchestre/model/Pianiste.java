package orchestre.model;

public class Pianiste implements IMusicien {
	
	private String prenom;
	
	private Piano instrument;
	
	public Pianiste() 
	{}
	

	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public Piano getInstrument() {
		return instrument;
	}



	public void setInstrument(Piano instrument) {
		this.instrument = instrument;
	}


	@Override
	public void jouer() {
		System.out.println("Le pianiste "+prenom+" joue ! "+instrument.son());
		
	}
}
