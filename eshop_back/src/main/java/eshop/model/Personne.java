package eshop.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import view.Views;

@Entity
@Table(name="person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_personne",columnDefinition = "ENUM('customer','supplier')" )
public abstract class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	protected Integer id;
	
	@Column(name="lastname",length = 20,nullable=false)
	@JsonView(Views.Common.class)
	protected String nom;
	@Column(name="firstname",length = 20,nullable=false)
	@JsonView(Views.Common.class)
	protected String prenom;
	@Enumerated(EnumType.STRING)
	@Column(name="gender",nullable=false)
	@JsonView(Views.Common.class)
	protected Genre civilite;
	
	

	public Personne(Integer id, String nom, String prenom,Genre civilite) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite=civilite;
	}
	
	public Personne() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	public Genre getCivilite() {
		return civilite;
	}

	public void setCivilite(Genre civilite) {
		this.civilite = civilite;
	}

	
	
	
}
