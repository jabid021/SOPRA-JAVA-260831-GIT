package duck.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("employee")
public class Employe extends Compte{
	
	@Column(name="sal", columnDefinition = "DECIMAL(6,2)" )
	private double salaire;
	
	@ManyToOne
	@JoinColumn(name="boutique")
	private Boutique boutique;

	public Employe() {}
	
	public Employe(Integer id, String login, String password, String nom, String prenom, Civilite civilite, double salaire,Boutique boutique) {
		super(id, login, password, nom, prenom, civilite);
		this.salaire = salaire;
		this.boutique=boutique;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	
	
	
	public Boutique getBoutique() {
		return boutique;
	}

	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}

	@Override
	public String toString() {
		return "Employe [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + ", salaire=" + salaire + ", boutique=" +boutique+"]";
	}
	

}
