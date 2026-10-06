package hopital.model;

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
@Table(name="visite")
public class Visite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	@Column(name="date_visite", nullable = false)
	private LocalDate dateVisite;
	private double prix;
	private int salle;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="id_medecin",nullable = false)
	private Medecin medecin;
	
	public Visite() {}
	
	public Visite(Integer numero, LocalDate dateVisite, double prix, int salle, Patient patient, Medecin medecin) {
		this.numero = numero;
		this.dateVisite = dateVisite;
		this.prix = prix;
		this.salle = salle;
		this.patient = patient;
		this.medecin = medecin;
	}
	
	public Visite(Patient patient, Medecin medecin) {
		this.dateVisite = LocalDate.now();
		this.prix = 20;
		this.salle = medecin.getSalle();
		this.patient = patient;
		this.medecin = medecin;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public LocalDate getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public String toString() {
		return "Visite [numero=" + numero + ", dateVisite=" + dateVisite + ", prix=" + prix + ", salle=" + salle
				+ ", patient=" + patient + ", medecin=" + medecin.getLogin() + "]";
	}
	
	
}
