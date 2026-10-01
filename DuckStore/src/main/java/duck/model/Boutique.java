package duck.model;

public class Boutique {

	private Integer id;
	private String nom;
	private Adresse adresse;
	
	
	public Boutique(Integer id, String nom, String numero,String voie,String ville,String cp) {

		this.id = id;
		this.nom = nom;
		this.adresse = new Adresse(numero,voie,ville,cp);
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


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	@Override
	public String toString() {
		return "Boutique [id=" + id + ", nom=" + nom + ", adresse=" + adresse + "]";
	}

}
