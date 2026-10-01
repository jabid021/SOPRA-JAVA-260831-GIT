package duck.model;

public class Boutique {

	private int id;
	private String nom;
	private Adresse adresse;
	
	
	public Boutique(int id, String nom, Adresse adresse) {

		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	public String toString() {
		return "Boutique [id=" + id + ", nom=" + nom + "]";
	}
	
	
	
}
