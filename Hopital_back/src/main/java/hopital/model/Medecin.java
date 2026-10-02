package hopital.model;

import java.util.ArrayList;
import java.util.List;

public class Medecin extends Compte{
	private transient int salle;
	private transient List<Visite> visites = new ArrayList();
	
	public Medecin(Integer id, String login, String password) {
		super(id, login, password);
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	@Override
	public String toString() {
		return "Medecin [id=" + id + ", login=" + login + ", password=" + password + ", salle=" + salle + ", visites="
				+ visites + "]";
	}
	
	
}
