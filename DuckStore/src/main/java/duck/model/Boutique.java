package duck.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity //OBLIGATOIRE
@Table(name="shop")
public class Boutique {

	@Id //OBLIGATOIRE
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //SEMI-OBLIGATOIRE
	private Integer id;
	
	@Column(name="name",length = 30, nullable = false)
	private String nom;
	
	@Embedded
	private Adresse adresse;
	
	
	@OneToMany(mappedBy="boutique")
	private List<Employe> staff;
	
	@OneToMany(mappedBy = "boutique")
	private List<Jeu> catalogue;
	
	
	public Boutique() {} //OBLIGATOIRE
	
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

	

	public List<Employe> getStaff() {
		return staff;
	}

	public void setStaff(List<Employe> staff) {
		this.staff = staff;
	}

	
	
	public List<Jeu> getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(List<Jeu> catalogue) {
		this.catalogue = catalogue;
	}

	@Override
	public String toString() {
		return "Boutique [id=" + id + ", nom=" + nom + ", adresse=" + adresse + "]";
	}

}
