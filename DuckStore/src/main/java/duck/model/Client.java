package duck.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue("customer")
public class Client extends Compte {
	
	@Column(length = 30)
    private String email;
	@Embedded
    private Adresse adresse;
	
	//Avec une table de Jointure, on va obtenir 2 fk, ici l'id du Client (fk principale) + id du Jeu  (fk inverse)
	@ManyToMany
	@JoinTable(
			name="achats",
			joinColumns =  @JoinColumn(name="client"),
			inverseJoinColumns = @JoinColumn(name="jeu"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"client","jeu"})
			)
    private List<Jeu> achats = new ArrayList();

	@OneToOne(mappedBy = "client")
	private Carte carte;
    
    public Client() {}
    
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


	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + ", email=" + email + ", adresse=" + adresse 
				+ "]";
	}
    
	
    
}


