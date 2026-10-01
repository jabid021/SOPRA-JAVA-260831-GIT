package duck.model;

import java.time.LocalDate;

public class Hybride extends Console {

	private int autonomie;
	private boolean dockInclus;
	
	public Hybride(Integer id, String nom, double prix, LocalDate dateSortie, int autonomie, boolean dockInclus) {
		super(id, nom, prix, dateSortie);
		this.autonomie = autonomie;
		this.dockInclus = dockInclus;
	}

	public int getAutonomie() {
		return autonomie;
	}

	public void setAutonomie(int autonomie) {
		this.autonomie = autonomie;
	}

	public boolean isDockInclus() {
		return dockInclus;
	}

	public void setDockInclus(boolean dockInclus) {
		this.dockInclus = dockInclus;
	}

	@Override
	public String toString() {
		return "Hybride [id=" + id + ", nom=" + nom + ", prix=" + prix + ", dateSortie=" + dateSortie + ", autonomie="
				+ autonomie + ", dockInclus=" + dockInclus + "]";
	}

	
	
	
}
