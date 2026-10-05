package quest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Stagiaire extends Personne {
	
	@Column(length = 35)
	private String email;
	@Embedded
	private Adresse adresse;
	@ManyToOne
	@JoinColumn(name="filiere")
	private Filiere filiere;

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



	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", genre=" + genre + ", email=" + email + ", adresse=" + adresse + ", filiere=" + filiere
				+ "]";
	}

}
