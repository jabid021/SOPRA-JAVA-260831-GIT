package duck.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="loyalty")
public class Carte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(columnDefinition = "varchar(30)" , unique = true,  nullable = false )
	private String numero;
	private int points;
	
	//@XToY
	// Ici dans la classe Carte, le X correspond à la relation INVERSE (Un client a combien de carte ?) 
	// Ici dans la classe Carte, le Y correspond au nombre de client associe à une carte
	@OneToOne
	@JoinColumn(name="client",nullable = false)
	private Client client;
	
	public Carte() {}
	
	
	public Carte(Integer id, String numero, int points,Client client) {
		this.id = id;
		this.numero = numero;
		this.points = points;
		this.client=client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Carte [id=" + id + ", numero=" + numero + ", points=" + points + ", client=" + client + "]";
	}

	
	
	
	

}
