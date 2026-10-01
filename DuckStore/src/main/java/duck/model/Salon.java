package duck.model;

import java.time.LocalDate;

public class Salon extends Console {

	private boolean lecteur;

	public Salon(Integer id, String nom, double prix, LocalDate dateSortie, boolean lecteur) {
		super(id, nom, prix, dateSortie);
		this.lecteur = lecteur;
	}

	public boolean isLecteur() {
		return lecteur;
	}

	public void setLecteur(boolean lecteur) {
		this.lecteur = lecteur;
	}

	@Override
	public String toString() {
		return "Salon [lecteur=" + lecteur + "]";
	}
	
	
}
