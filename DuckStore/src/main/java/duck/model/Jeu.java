package duck.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="game")
public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="title", length = 50, nullable = false )
	private String titre;
	
	@Column(columnDefinition = "DECIMAL(5,2)") //Avec DECIMAL(x,y) => x correspond au nombre total de digit (avant + apres la virgule) , y correspond au nombre de deci apres la virgule, fait un Round si trop de deci
	private double prix;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Genre> genres;
	
	@ManyToOne
	private Console console;
	
	@ManyToOne
	private Boutique boutique;

	public Jeu() {}
	
	public Jeu(Integer id, String titre, Console console,Boutique boutique, Set<Genre> genres,double prix) {
		
		this.id = id;
		this.titre = titre;
		this.boutique = boutique;
		this.console = console;
		this.genres = genres;
		this.prix=prix;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Boutique getBoutique() {
		return boutique;
	}


	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}


	public Console getConsole() {
		return console;
	}


	public void setConsole(Console console) {
		this.console = console;
	}


	public Set<Genre> getGenres() {
		return genres;
	}


	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public String toString() {
		return "Jeu [id=" + id + ", titre=" + titre + ", boutique=" + boutique + ", console=" + console + ", genres="
				+ genres + ", prix= "+prix+"]";
	}
	
	
	
}
