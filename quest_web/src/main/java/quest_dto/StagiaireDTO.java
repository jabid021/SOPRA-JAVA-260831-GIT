package quest_dto;

import org.springframework.beans.BeanUtils;

import quest.model.Adresse;
import quest.model.Genre;
import quest.model.Stagiaire;

public class StagiaireDTO {

	private Integer id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private Genre genre;
	private String email;
	private Adresse adresse;
	private Integer idFiliere;
	private String libelleFiliere;
	
	public static StagiaireDTO convert(Stagiaire stagiaire) 
	{
		StagiaireDTO stagiaireDTO = new StagiaireDTO();
		/*stagiaireDTO.id = stagiaire.getId();
		stagiaireDTO.login = stagiaire.getLogin();
		stagiaireDTO.password = stagiaire.getPassword();
		stagiaireDTO.nom = stagiaire.getNom();
		stagiaireDTO.prenom=stagiaire.getPrenom();
		stagiaireDTO.genre = stagiaire.getGenre();
		stagiaireDTO.email = stagiaire.getEmail();
		stagiaireDTO.adresse=stagiaire.getAdresse();
		*/
		BeanUtils.copyProperties(stagiaire, stagiaireDTO);
		stagiaireDTO.idFiliere=stagiaire.getFiliere().getId();
		stagiaireDTO.libelleFiliere=stagiaire.getFiliere().getLibelle();
		return stagiaireDTO;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
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

	public Integer getIdFiliere() {
		return idFiliere;
	}

	public void setIdFiliere(Integer idFiliere) {
		this.idFiliere = idFiliere;
	}

	public String getLibelleFiliere() {
		return libelleFiliere;
	}

	public void setLibelleFiliere(String libelleFiliere) {
		this.libelleFiliere = libelleFiliere;
	}
	
	
	
	
}
