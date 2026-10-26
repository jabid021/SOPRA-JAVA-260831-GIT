package quest_dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import quest.model.Formateur;
import quest.model.Matiere;
import quest.model.Module;

public class ModuleDTO {

	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate debut;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fin;
	private Matiere matiere;
	private String infosFormateur;
	private FiliereDTO filiere;
	
	
	
	public static ModuleDTO convert(Module module) 
	{
		ModuleDTO dto = new ModuleDTO();
		BeanUtils.copyProperties(module, dto);
		dto.filiere=FiliereDTO.convert(module.getFiliere());
		if(module.getFormateur()!=null) 
		{
			dto.infosFormateur = module.getFormateur().getPrenom()+" "+module.getFormateur().getNom();
		}
		return dto;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public Matiere getMatiere() {
		return matiere;
	}



	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public String getInfosFormateur() {
		return infosFormateur;
	}



	public void setInfosFormateur(String infosFormateur) {
		this.infosFormateur = infosFormateur;
	}



	public FiliereDTO getFiliere() {
		return filiere;
	}



	public void setFiliere(FiliereDTO filiere) {
		this.filiere = filiere;
	}
	
	
}
