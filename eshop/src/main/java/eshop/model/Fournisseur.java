package eshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("supplier")
public class Fournisseur extends Personne {

	@Column(name="company",length = 20)
	private String societe;

	public Fournisseur(Integer id, String nom, String prenom, Genre civilite, String societe) {
		super(id, nom, prenom, civilite);
		this.societe = societe;
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
