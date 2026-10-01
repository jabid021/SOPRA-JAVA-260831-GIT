package duck.model;

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

@Entity
@Table(name="account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_account", columnDefinition = "ENUM('employee','customer')")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(length = 30, unique = true, nullable = false )
	protected String login;
	@Column(length = 60, nullable = false )
	protected String password;
	@Column(name="lastname",length = 20, nullable = false )
	protected String nom;
	@Column(name="firstname",length = 20, nullable = false )
	protected String prenom;
	
	@Enumerated(EnumType.STRING)
	@Column(name="civ",nullable = false)
	protected Civilite civilite;
	
	public Compte() {}

	public Compte(Integer id, String login, String password, String nom, String prenom, Civilite civilite) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
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


	

	public Civilite getCivilite() {
		return civilite;
	}



	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
}
