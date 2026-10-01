package duck.model;

import java.time.LocalDate;

public class Portable extends Console {

	private int autonomie;
	private double ecran;
	
	public Portable(Integer id, String nom, double prix, LocalDate dateSortie, int autonomie, double ecran) {
		super(id, nom, prix, dateSortie);
		this.autonomie = autonomie;
		this.ecran = ecran;
	}

	public int getAutonomie() {
		return autonomie;
	}

	public void setAutonomie(int autonomie) {
		this.autonomie = autonomie;
	}

	public double getEcran() {
		return ecran;
	}

	public void setEcran(double ecran) {
		this.ecran = ecran;
	}

	@Override
	public String toString() {
		return "Portable [id=" + id + ", nom=" + nom + ", prix=" + prix + ", dateSortie=" + dateSortie + ", autonomie="
				+ autonomie + ", ecran=" + ecran + "]";
	}

	
	
	
	
}
