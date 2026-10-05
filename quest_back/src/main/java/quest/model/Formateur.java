package quest.model;

import jakarta.persistence.Entity;

@Entity
public class Formateur extends Personne{
	
	private boolean admin;
	
	public Formateur() {}

	public Formateur(Integer id, String login, String password, String nom, String prenom, Genre genre, boolean admin) {
		super(id, login, password, nom, prenom, genre);
		this.admin = admin;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Formateur [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", genre=" + genre + ", admin=" + admin + "]";
	}

	
}
