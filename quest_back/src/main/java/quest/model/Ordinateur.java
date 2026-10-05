package quest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ordinateur")
public class Ordinateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	@Column(length = 20,nullable = false)
	private String marque;
	
	@Column(columnDefinition = "int default 0")
	private int ram;
	
	@OneToOne
	@JoinColumn(name="utilisateur")
	private Stagiaire utilisateur;
	
	
	
	public Ordinateur() {}
	
	public Ordinateur(Integer numero, String marque, int ram, Stagiaire utilisateur) {
		this.numero = numero;
		this.marque = marque;
		this.ram = ram;
		this.utilisateur = utilisateur;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public Stagiaire getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Stagiaire utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Ordinateur [numero=" + numero + ", marque=" + marque + ", ram=" + ram + ", utilisateur=" + utilisateur
				+ "]";
	}

	
}