package quest.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import view.Views;

@Entity
public class Stagiaire extends Personne {
	
	@Column(length = 35)
	@JsonView(Views.Common.class)
	private String email;
	@Embedded
	@JsonView(Views.Common.class)
	private Adresse adresse;
	@ManyToOne
	@JoinColumn(name="filiere")
	@JsonView(Views.Stagiaire.class)
	private Filiere filiere;
	
	
	@OneToOne(mappedBy = "utilisateur")
	@JsonView(Views.Stagiaire.class)
	private Ordinateur ordinateur;

	public Stagiaire() {}
	
	public Stagiaire(Integer id, String login, String password, String nom, String prenom, Genre genre, String email, String numero, String voie, String ville, String cp,Filiere filiere) {
		super(id, login, password, nom, prenom, genre);
		this.email = email;
		this.adresse = new Adresse (numero, voie, ville, cp);
		this.filiere=filiere;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	

	public Filiere getFiliere() {
		return filiere;
	}



	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	
	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

	public String getInfos() 
	{
		return id+" - "+prenom+" "+nom;
	}

	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", genre=" + genre + ", email=" + email + ", adresse=" + adresse + ", filiere=" + filiere
				+ "]";
	}

}
