package duck.model;

import java.time.LocalDate;

public abstract class Console {

	protected Integer id;
	protected String nom;
	protected double prix;
	protected LocalDate dateSortie;
	
	public Console(Integer id, String nom, double prix, LocalDate dateSortie) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.dateSortie = dateSortie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public LocalDate getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	
	
}
