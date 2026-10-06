package eshop.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("supplier")
public class Fournisseur extends Personne {

	@Column(name="company",length = 20)
	private String societe;

	@OneToMany(mappedBy = "fournisseur")
	private List<Produit> stock;
	
	public Fournisseur() {}
	
	public Fournisseur(Integer id, String nom, String prenom, Genre civilite, String societe) {
		super(id, nom, prenom, civilite);
		this.societe = societe;
	}

	
	public List<Produit> getStock() {
		return stock;
	}


	public void setStock(List<Produit> stock) {
		this.stock = stock;
	}


	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite + ", societe="
				+ societe + "]";
	}
	
	
}
