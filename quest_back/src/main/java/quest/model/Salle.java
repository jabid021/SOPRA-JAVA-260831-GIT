package quest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="salle")
public class Salle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 20,nullable = false)
	private String nom;
	
	@Embedded
	private Adresse addrese;
	
	public Salle() {}
	public Salle(Integer id, String nom, String numero, String voie, String ville, String cp) {
		this.id = id;
		this.nom = nom;
		this.addrese = new Adresse(numero, voie, ville,  cp);
		
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


	public Adresse getAddrese() {
		return addrese;
	}


	public void setAddrese(Adresse addrese) {
		this.addrese = addrese;
	}


	@Override
	public String toString() {
		return "Salle [id=" + id + ", nom=" + nom + ", addrese=" + addrese + "]";
	}
	
}
