package duck.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="console")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Console {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="console_id")
	protected Integer id;
	
	@Column(name="name",length = 20, nullable = false)
	protected String nom;
	
	@Column(name="price",columnDefinition = "DECIMAL(5,2)")
	protected double prix;
	
	@Column(name="release_date", nullable = false)
	protected LocalDate dateSortie;
	
	public Console() {}
	
	public Console(Integer id, String nom, double prix, LocalDate dateSortie) {
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
