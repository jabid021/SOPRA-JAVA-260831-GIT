package eshop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("customer")
public class Client extends Personne {

	@Column(name="birthdate")
	private LocalDate dateNaissance;
	
	@Embedded
	private Adresse adresse;
	
	@OneToMany(mappedBy = "acheteur")
	//@JoinTable(name="achats",joinColumns =@JoinColumn(name="acheteur"),inverseJoinColumns = @JoinColumn(name="produit"))
	private List<Achat> achats = new ArrayList();
	
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

	
	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite
				+ ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + "]";
	}

	
	
	
	
	
}
