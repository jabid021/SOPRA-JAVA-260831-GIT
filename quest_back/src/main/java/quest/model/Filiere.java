package quest.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="filiere")
public class Filiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 35,nullable = false)
    private String libelle;
    private LocalDate debut;
    private LocalDate fin;
    
    @ManyToOne
    @JoinColumn(name="salle")
    private Salle salle;
    
    public Filiere() {}
    
	public Filiere(Integer id, String libelle, LocalDate debut, LocalDate fin,Salle salle) {
		this.id = id;
		this.libelle = libelle;
		this.debut = debut;
		this.fin = fin;
		this.salle=salle;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public LocalDate getDebut() {
		return debut;
	}
	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}
	public LocalDate getFin() {
		return fin;
	}
	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}


	@Override
	public String toString() {
		return "Filiere [id=" + id + ", libelle=" + libelle + ", debut=" + debut + ", fin=" + fin + ", salle=" + salle
				+ "]";
	}

}
