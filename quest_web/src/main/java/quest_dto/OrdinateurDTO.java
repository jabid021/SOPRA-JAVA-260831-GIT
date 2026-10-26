package quest_dto;

import org.springframework.beans.BeanUtils;

import quest.model.Ordinateur;

public class OrdinateurDTO {

	private Integer numero;
	private String marque;
	private int ram;
	private StagiaireDTO stagiaire;
	
	public static OrdinateurDTO convert(Ordinateur ordinateur) 
	{
		OrdinateurDTO dto = new OrdinateurDTO();
		BeanUtils.copyProperties(ordinateur, dto);
		if(ordinateur.getUtilisateur()!=null) 
		{
			dto.stagiaire=StagiaireDTO.convert(ordinateur.getUtilisateur());
		}
		return dto;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public StagiaireDTO getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(StagiaireDTO stagiaire) {
		this.stagiaire = stagiaire;
	}
	
}
