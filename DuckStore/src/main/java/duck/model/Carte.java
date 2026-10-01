package duck.model;

public class Carte {
	
	private Integer id;
	private String numero;
	private int points;
	private Client client;
	
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
