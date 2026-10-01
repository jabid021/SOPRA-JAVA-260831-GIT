package duck.model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Compte {
    private String email;
    private Adresse adresse;
    private List<Jeu> achats = new ArrayList<>();

    public Client(Integer id, String login, String password,String nom, String prenom, Civilite civilite,String email, Adresse adresse) {
        super(id, login, password, nom, prenom, civilite);
        this.email = email;
        this.adresse = adresse;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Jeu> getAchats() {
		return achats;
	}

	public void setAchats(List<Jeu> achats) {
		this.achats = achats;
	}
    
	@Override
	public String toString() {
		return "Client [email=" + email + ", adresse=" + adresse + ", achats=" + achats + "]";
	
    
}


