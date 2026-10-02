package eshop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("customer")
public class Client extends Personne {

	@Column(name="birthdate")
	private LocalDate dateNaissance;
	
	@Embedded
	private Adresse adresse;
	
	@ManyToMany
	private List<Produit> achats = new ArrayList();
	
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

	
	public List<Produit> getAchats() {
		return achats;
	}

	public void setAchats(List<Produit> achats) {
		this.achats = achats;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite
				+ ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + "]";
	}

	
	
	
	
	
}
