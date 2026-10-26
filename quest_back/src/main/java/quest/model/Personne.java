package quest.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import view.Views;

@Entity
@Table(name="personne")
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_personne",columnDefinition = "ENUM('Stagiaire','Formateur')")
public abstract class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	protected Integer id;
	@Column(length = 30,nullable = false,unique = true)
	@JsonView(Views.Common.class)
	protected String login;
	@Column(length = 60,nullable = false)
	@JsonView(Views.Common.class)
	protected String password;
	@Column(length = 25,nullable = false)
	@JsonView(Views.Common.class)
	protected String nom;
	@Column(length = 25,nullable = false)
	@JsonView(Views.Common.class)
	protected String prenom;
	
	@Enumerated(EnumType.STRING)
	@Column(name="civilite",nullable = false)
	@JsonView(Views.Common.class)
	protected Genre genre;
	
	public Personne() {}
	
	public Personne(Integer id, String login, String password, String nom, String prenom, Genre genre) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
	}

	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getInfos() 
	{
		return id+" - "+prenom+" "+nom;
	}
}
