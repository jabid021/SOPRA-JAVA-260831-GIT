package duck.model;

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
	
	@Column(name="price",columnDefinition = "DECIMAL(5,2)")
	private double prix;
	@Column(name="date_achat")
	private LocalDate dateAchat;
	
	@ManyToOne
	@JoinColumn(name="acheteur",nullable = false)
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="game",nullable = false)
	private Jeu jeu;
	
	@ManyToOne
	private Paiement paiement;
	
	
	public Achat() {}


	public Achat(Client client, Jeu jeu,Paiement paiement) {
		this.client = client;
		this.jeu = jeu;
		this.dateAchat=LocalDate.now();
		this.prix=jeu.getPrix();
		this.paiement=paiement;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public LocalDate getDateAchat() {
		return dateAchat;
	}


	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Jeu getJeu() {
		return jeu;
	}


	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}


	public Paiement getPaiement() {
		return paiement;
	}


	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}


	@Override
	public String toString() {
		return "Achat [id=" + id + ", prix=" + prix + ", dateAchat=" + dateAchat + ", client=" + client + ", jeu=" + jeu
				+ ", paiement=" + paiement + "]";
	}


	
	
	
	
	
}
