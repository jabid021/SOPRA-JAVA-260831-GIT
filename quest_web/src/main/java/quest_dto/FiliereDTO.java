package quest_dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import quest.model.Filiere;
import quest.model.Salle;

public class FiliereDTO {

	private Integer id;
	private String libelle;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate debut;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fin;
	private Salle salle;
	private List<StagiaireDTO> stagiaires;
	
	public static FiliereDTO convert(Filiere filiere) 
	{
		FiliereDTO dto = new FiliereDTO();
		BeanUtils.copyProperties(filiere, dto);
		return dto;
	}
	
	public static FiliereDTO convertWithStagiaires(Filiere filiere) 
	{
		FiliereDTO dto = new FiliereDTO();
		BeanUtils.copyProperties(filiere, dto);
		dto.stagiaires=filiere.getStagiaires().stream().map(s->StagiaireDTO.convert(s)).toList();
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public List<StagiaireDTO> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<StagiaireDTO> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	
}
