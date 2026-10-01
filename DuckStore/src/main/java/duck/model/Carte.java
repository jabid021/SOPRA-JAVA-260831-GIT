package duck.model;

public class Carte {
	
	private Integer id;
	private String numero;
	private int points;
	
	public Carte(Integer id, String numero, int points) {
		this.id = id;
		this.numero = numero;
		this.points = points;
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

	@Override
	public String toString() {
		return "Carte [id=" + id + ", numero=" + numero + ", points=" + points + "]";
	}
	
	
	

}
