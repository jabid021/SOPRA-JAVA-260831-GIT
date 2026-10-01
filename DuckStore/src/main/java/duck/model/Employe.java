package duck.model;

public class Employe extends Compte{
	
	private double salaire;

	protected Employe(Integer id, String login, String password, String nom, String prenom, Civilite civilite, double salaire) {
		super(id, login, password, nom, prenom, civilite);
		this.salaire = salaire;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	
	
	@Override
	public String toString() {
		return "TPT > Groupe 2 > all\nEmploye [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + ", salaire=" + salaire + "]";
	}
	

}
