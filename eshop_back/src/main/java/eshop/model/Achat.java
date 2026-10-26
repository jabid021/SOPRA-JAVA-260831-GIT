package eshop.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import view.Views;

@Entity
@Table(name="achat")
public class Achat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private Integer id;
	@Column(name="date_achat",nullable = false)
	@JsonView(Views.Common.class)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateAchat;
	@JsonView(Views.Common.class)
	private int quantite;
	
	
	@ManyToOne
	@JoinColumn(name="acheteur",nullable=false)
	@JsonView(Views.ProduitWithVentes.class)
	private Client acheteur;
	
	@ManyToOne
	@JoinColumn(name="produit",nullable=false)
	@JsonView(Views.ClientWithAchats.class)
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
