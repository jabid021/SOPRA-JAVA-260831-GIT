package quest.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import view.Views;

@Entity
@Table(name="filiere")
public class Filiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private Integer id;
	@Column(length = 35,nullable = false)
	
	@NotBlank
	@Size(min = 10, max = 20, message = "La taille du libelle n'est pas conforme (entre 10 et 20 stp..) ")
	@JsonView(Views.Common.class)
    private String libelle;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.Common.class)
    private LocalDate debut;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.Common.class)
    private LocalDate fin;
    
    @ManyToOne
    @JoinColumn(name="salle")
    @JsonView(Views.Filiere.class)
    private Salle salle;
    
    @OneToMany(mappedBy = "filiere")
    @JsonView(Views.FiliereWithStagiaires.class)
    private List<Stagiaire> stagiaires;
    
    
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
	
	public String getInfos() 
	{
		return id+" - "+libelle;
	}

	

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	@Override
	public String toString() {
		return "Filiere [id=" + id + ", libelle=" + libelle + ", debut=" + debut + ", fin=" + fin + ", salle=" + salle
				+ "]";
	}

}
