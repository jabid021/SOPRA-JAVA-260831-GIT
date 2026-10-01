package duck.model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Compte {
    private String email;
    private Adresse adresse;
    private List<Jeu> achats = new ArrayList();

    public Client(Integer id, String login, String password,String nom, String prenom, Civilite civilite,String email, String numero,String voie,String ville,String cp) {
        super(id, login, password, nom, prenom, civilite);
        this.email = email;
        this.adresse = new Adresse(numero,voie,ville,cp);
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
		return "Client [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + ", email=" + email + ", adresse=" + adresse 
				+ "]";
	}
    
	
    
}


