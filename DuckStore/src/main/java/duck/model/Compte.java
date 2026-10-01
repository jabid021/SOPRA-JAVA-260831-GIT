package duck.model;

public abstract class Compte {
	protected Integer id;
	protected String login;
	protected String password;
	protected String nom;
	protected String prenom;
	protected Civilite civilite;
	

	protected Compte(Integer id, String login, String password, String nom, String prenom, Civilite civilite) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	

	public Civilite getCivilite() {
		return civilite;
	}



	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}



	@Override
	public String toString() {
		return "Groupe 2 > all \nCompte TPT [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + "]";
	}
	
	
}
