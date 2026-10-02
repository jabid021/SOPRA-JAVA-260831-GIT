package duck.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="home")
public class Salon extends Console {

	private boolean lecteur;

	public Salon() {}
	
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
		return "Salon [id=" + id + ", nom=" + nom + ", prix=" + prix + ", dateSortie=" + dateSortie + ", lecteur="
				+ lecteur + "]";
	}

	
	
	
}
