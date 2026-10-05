package quest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="matiere")
public class Matiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 25,nullable = false)
	private String libelle;
	
	public Matiere() {}
	public Matiere(Integer id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}


	public Integer getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String toString() {
		return "Matiere [id=" + id + ", libelle=" + libelle + "]";
	}

}
