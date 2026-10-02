package eshop.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("customer")
public class Client extends Personne {

	@Column(name="birthdate")
	private LocalDate dateNaissance;
	
	@Embedded
	private Adresse adresse;
	
	public Client() {}

	public Client(Integer id, String nom, String prenom, Genre civilite, LocalDate dateNaissance, String numero, String voie, String ville, String cp) {
		super(id, nom, prenom, civilite);
		this.dateNaissance = dateNaissance;
		this.adresse = new Adresse(numero,voie,ville,cp);
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite
				+ ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + "]";
	}

	
	
	
	
	
}
