package orchestre.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Guitariste implements IMusicien{

	private String prenom;
	
	@Autowired
	@Qualifier("guitare")
	private IInstrument instrument;
	
	
	public Guitariste() 
	{
		this.prenom="Jordan";
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	@Override
	public void jouer() {
		System.out.println("le guitariste "+prenom+" joue ! "+instrument.son());
	}

	@Override
	public String toString() {
		return "Guitariste [prenom=" + prenom + "]";
	}
	

}
