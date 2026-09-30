package hopital.model;

public class Secretaire extends Compte {

	public Secretaire(Integer id, String login, String password) {
		super(id, login, password);
	}

	@Override
	public String toString() {
		return "Secretaire [id=" + id + ", login=" + login + ", password=" + password + "]";
	}

}
