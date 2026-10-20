package eshop.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="achat")
public class Achat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="date_achat",nullable = false)
	private LocalDate dateAchat;
	private int quantite;
	
	
	@ManyToOne
	@JoinColumn(name="acheteur",nullable=false)
	private Client acheteur;
	
	@ManyToOne
	@JoinColumn(name="produit",nullable=false)
	private Produit produit;
	
	
	public Achat() {}


	
	public Achat(Integer id, LocalDate dateAchat, int quantite, Client acheteur, Produit produit) {
		this.id = id;
		this.dateAchat = dateAchat;
		this.quantite = quantite;
		this.acheteur = acheteur;
		this.produit = produit;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDateAchat() {
		return dateAchat;
	}


	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public Client getAcheteur() {
		return acheteur;
	}


	public void setAcheteur(Client acheteur) {
		this.acheteur = acheteur;
	}


	public Produit getProduit() {
		return produit;
	}


	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	@Override
	public String toString() {
		return "Achat [id=" + id + ", dateAchat=" + dateAchat + ", quantite=" + quantite + ", acheteur=" + acheteur
				+ ", produit=" + produit + "]";
	}
	
	
	
}
